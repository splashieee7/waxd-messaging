package com.waxd.messaging.domain.conversation.usecase.participant.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface ResolveConversationIdResult {
    data object EmptyDestinations : ResolveConversationIdResult

    data object NotResolved : ResolveConversationIdResult

    data class Resolved(
        val conversationId: ConversationId,
    ) : ResolveConversationIdResult
}
