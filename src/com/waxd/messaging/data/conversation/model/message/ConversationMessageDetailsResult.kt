package com.waxd.messaging.data.conversation.model.message

import com.waxd.messaging.datamodel.data.ConversationMessageData

internal data class ConversationMessageDetailsResult(
    val message: ConversationMessageData,
    val details: ConversationMessageDetails,
)
