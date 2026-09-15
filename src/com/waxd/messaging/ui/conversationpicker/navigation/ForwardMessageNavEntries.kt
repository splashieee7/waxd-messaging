package com.waxd.messaging.ui.conversationpicker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.R
import com.waxd.messaging.ui.conversation.navigation.ConversationNavKey
import com.waxd.messaging.ui.conversationlist.navigation.ConversationListNavKey
import com.waxd.messaging.ui.conversationpicker.ConversationPickerScreen
import com.waxd.messaging.ui.conversationpicker.ConversationPickerViewModel
import com.waxd.messaging.ui.conversationpicker.host.forward.ForwardMessageNavEvent
import com.waxd.messaging.ui.conversationpicker.host.forward.ForwardMessageViewModel
import com.waxd.messaging.ui.conversationpicker.host.forward.rememberForwardMessageEffectHandler
import com.waxd.messaging.ui.conversationpicker.model.ConversationPickerLabels
import com.waxd.messaging.ui.core.CollectEvents
import com.waxd.messaging.ui.navigation.LocalNavigator
import com.waxd.messaging.ui.navigation.SeededViewModelStoreOwner
import com.waxd.messaging.ui.navigation.paneTitleMetadata
import com.waxd.messaging.util.UiUtils

internal fun EntryProviderScope<NavKey>.forwardMessageEntries() {
    entry<ForwardMessageNavKey>(
        metadata = paneTitleMetadata(R.string.forward_message_activity_title),
        content = forwardMessageRouteContent(),
    )
}

private fun forwardMessageRouteContent(): @Composable (ForwardMessageNavKey) -> Unit {
    return { navKey ->
        val navigator = LocalNavigator.current
        val defaultArgs = remember(navKey) {
            forwardMessageDefaultArgs(navKey = navKey)
        }

        SeededViewModelStoreOwner(defaultArgs = defaultArgs) {
            val screenModel = hiltViewModel<ForwardMessageViewModel>()
            val uiState by screenModel.uiState.collectAsStateWithLifecycle()
            val effectHandler = rememberForwardMessageEffectHandler(
                onTargetSelected = screenModel::onTargetSelected,
                onSendToSelected = { targets, draft ->
                    screenModel.onSendToTargets(
                        targets = targets,
                        draft = draft,
                    ) {
                        UiUtils.showToastAtBottom(R.string.send_message_failure)
                    }
                },
            )

            CollectEvents(events = screenModel.navigationEvents) { event ->
                when (event) {
                    is ForwardMessageNavEvent.OpenConversation -> {
                        navigator.reset(
                            destinations = listOf(
                                ConversationListNavKey,
                                ConversationNavKey(conversationId = event.conversationId),
                            ),
                        )
                    }

                    is ForwardMessageNavEvent.Close -> {
                        navigator.reset(
                            destinations = listOf(ConversationListNavKey),
                        )
                    }
                }
            }

            ConversationPickerScreen(
                screenModel = hiltViewModel<ConversationPickerViewModel>(),
                isInitialDraftLoading = uiState.isLoading,
                initialDraft = uiState.draft,
                effectHandler = effectHandler,
                onNavigateBack = navigator::back,
                allowMultiSelect = true,
                labels = ConversationPickerLabels.Forward,
            )
        }
    }
}
