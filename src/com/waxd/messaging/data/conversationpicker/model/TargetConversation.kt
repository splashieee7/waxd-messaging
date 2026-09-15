package com.waxd.messaging.data.conversationpicker.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal data class TargetConversation(
    val conversationId: ConversationId,
    val name: String,
    val icon: String?,
    val normalizedDestination: String?,
    val isGroup: Boolean,
)
