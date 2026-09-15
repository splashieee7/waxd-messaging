package com.waxd.messaging.domain.conversationpicker.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface SendTarget {

    data class Conversation(
        val conversationId: ConversationId,
    ) : SendTarget

    data class Contact(
        val destination: String,
    ) : SendTarget
}
