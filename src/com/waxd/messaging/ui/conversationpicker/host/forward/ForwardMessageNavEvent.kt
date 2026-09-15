package com.waxd.messaging.ui.conversationpicker.host.forward

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface ForwardMessageNavEvent {

    data class OpenConversation(
        val conversationId: ConversationId,
    ) : ForwardMessageNavEvent

    data object Close : ForwardMessageNavEvent
}
