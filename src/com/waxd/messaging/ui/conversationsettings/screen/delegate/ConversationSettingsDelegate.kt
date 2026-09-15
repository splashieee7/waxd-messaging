package com.waxd.messaging.ui.conversationsettings.screen.delegate

import androidx.lifecycle.SavedStateHandle
import com.waxd.messaging.data.blockedparticipants.repository.BlockedParticipantsRepository
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.conversation.repository.ConversationsRepository
import com.waxd.messaging.data.conversationsettings.model.SnoozeOption
import com.waxd.messaging.data.conversationsettings.repository.ConversationNotificationRepository
import com.waxd.messaging.data.conversationsettings.repository.ConversationSettingsRepository
import com.waxd.messaging.data.subscription.repository.ConversationSimSelectionRepository
import com.waxd.messaging.data.subscription.repository.SubscriptionsRepository
import com.waxd.messaging.datamodel.ParticipantRefresh
import com.waxd.messaging.di.core.ApplicationCoroutineScope
import com.waxd.messaging.domain.conversationsettings.usecase.SetConversationSelfParticipantId
import com.waxd.messaging.ui.conversationsettings.common.ConversationSettingsScreenDelegate
import com.waxd.messaging.ui.conversationsettings.screen.CONVERSATION_SETTINGS_CONVERSATION_ID_ARG
import com.waxd.messaging.ui.conversationsettings.screen.mapper.ConversationSettingsUiStateMapper
import com.waxd.messaging.ui.conversationsettings.screen.model.ConversationSettingsUiState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal interface ConversationSettingsDelegate :
    ConversationSettingsScreenDelegate<ConversationSettingsUiState> {
    fun setDestinationBlocked(blocked: Boolean)
    fun setArchived(archived: Boolean)
    fun setSelfParticipantId(selfParticipantId: ParticipantId)
    fun snooze(option: SnoozeOption)
    fun unsnooze()
}

internal class ConversationSettingsDelegateImpl @Inject constructor(
    private val repository: ConversationSettingsRepository,
    private val notificationRepository: ConversationNotificationRepository,
    private val subscriptionsRepository: SubscriptionsRepository,
    private val simSelectionRepository: ConversationSimSelectionRepository,
    private val mapper: ConversationSettingsUiStateMapper,
    private val conversationsRepository: ConversationsRepository,
    private val blockedParticipantsRepository: BlockedParticipantsRepository,
    private val setConversationSelfParticipantId: SetConversationSelfParticipantId,
    @param:ApplicationCoroutineScope private val applicationScope: CoroutineScope,
    savedStateHandle: SavedStateHandle,
) : ConversationSettingsDelegate {

    override val rootConversationId: ConversationId = requireNotNull(
        ConversationId.fromOrNull(savedStateHandle[CONVERSATION_SETTINGS_CONVERSATION_ID_ARG]),
    ) { "conversationId is required" }

    private val _state = MutableStateFlow(ConversationSettingsUiState(rootConversationId))
    override val state: StateFlow<ConversationSettingsUiState> = _state.asStateFlow()

    private val refreshTriggers = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    private val conversationIdFlow = MutableStateFlow(rootConversationId)
    private var isBound = false

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun bind(scope: CoroutineScope) {
        if (isBound) return
        isBound = true

        conversationIdFlow
            .flatMapLatest(::observeUiState)
            .onEach { _state.value = it }
            .launchIn(scope)
    }

    override fun setConversationId(conversationId: ConversationId) {
        if (conversationIdFlow.value == conversationId) return
        conversationIdFlow.value = conversationId
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeUiState(id: ConversationId): Flow<ConversationSettingsUiState> {
        val settings = refreshTriggers
            .onStart { emit(Unit) }
            .flatMapLatest { repository.getConversationSettings(id) }

        return combine(
            settings,
            subscriptionsRepository.observeActiveSubscriptions(),
            simSelectionRepository.observe(id),
        ) { data, subscriptions, selfIdOverride ->
            mapper.map(
                data = data,
                subscriptions = subscriptions,
                selfIdOverride = selfIdOverride,
            )
        }
    }

    override fun refresh() {
        ParticipantRefresh.refreshParticipantsIfNeeded()
        refreshTriggers.tryEmit(Unit)
    }

    override fun setDestinationBlocked(blocked: Boolean) {
        val normalizedDestination = _state.value.otherParticipant?.normalizedDestination ?: return

        applicationScope.launch {
            blockedParticipantsRepository.setDestinationBlocked(
                destination = normalizedDestination,
                conversationId = currentConversationId(),
                isBlocked = blocked,
            )
        }
    }

    override fun setArchived(archived: Boolean) {
        val conversationId = currentConversationId()

        applicationScope.launch {
            when {
                archived -> conversationsRepository.archiveConversation(conversationId)
                else -> conversationsRepository.unarchiveConversation(conversationId)
            }
        }
    }

    override fun setSelfParticipantId(selfParticipantId: ParticipantId) {
        if (_state.value.selfParticipantId == selfParticipantId) return

        applicationScope.launch {
            setConversationSelfParticipantId(
                conversationId = currentConversationId(),
                selfParticipantId = selfParticipantId,
            )
        }
    }

    override fun snooze(option: SnoozeOption) {
        notificationRepository.snooze(currentConversationId(), option)
        refresh()
    }

    override fun unsnooze() {
        notificationRepository.clearSnooze(currentConversationId())
        refresh()
    }

    private fun currentConversationId(): ConversationId {
        return conversationIdFlow.value
    }
}
