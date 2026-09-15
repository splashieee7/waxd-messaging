package com.waxd.messaging.ui.conversation.screen.model

import com.waxd.messaging.data.conversation.model.MessageId

internal sealed interface ConversationScreenNavEvent {

    data object CloseConversation : ConversationScreenNavEvent

    data class NavigateToMessageDetails(
        val messageId: MessageId,
    ) : ConversationScreenNavEvent

    data class ForwardMessage(
        val messageId: MessageId,
    ) : ConversationScreenNavEvent
}
