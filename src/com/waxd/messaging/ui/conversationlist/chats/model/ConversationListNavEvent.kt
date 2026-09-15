package com.waxd.messaging.ui.conversationlist.chats.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface ConversationListNavEvent {

    data object OpenNewChat : ConversationListNavEvent
    data object OpenArchivedConversations : ConversationListNavEvent
    data object OpenBlockedParticipants : ConversationListNavEvent
    data object OpenSettings : ConversationListNavEvent

    data class OpenConversation(
        val conversationId: ConversationId,
    ) : ConversationListNavEvent

    data class OpenConversationSettings(
        val conversationId: ConversationId,
    ) : ConversationListNavEvent

    data class CloseConversation(
        val conversationId: ConversationId,
    ) : ConversationListNavEvent
}
