package com.waxd.messaging.domain.conversationsettings.usecase

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.conversation.repository.ConversationsRepository
import com.waxd.messaging.data.subscription.repository.ConversationSimSelectionRepository
import javax.inject.Inject

internal fun interface SetConversationSelfParticipantId {
    suspend operator fun invoke(conversationId: ConversationId, selfParticipantId: ParticipantId)
}

internal class SetConversationSelfParticipantIdImpl @Inject constructor(
    private val simSelectionRepository: ConversationSimSelectionRepository,
    private val conversationsRepository: ConversationsRepository,
) : SetConversationSelfParticipantId {

    override suspend fun invoke(
        conversationId: ConversationId,
        selfParticipantId: ParticipantId,
    ) {
        if (conversationId.isBlank()) return

        simSelectionRepository.setSelectedSelfId(
            conversationId = conversationId,
            selfId = selfParticipantId,
        )
        conversationsRepository.setConversationSelfId(
            conversationId = conversationId,
            selfId = selfParticipantId,
        )
    }
}
