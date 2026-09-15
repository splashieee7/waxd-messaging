package com.waxd.messaging.ui.conversation.messages.ui.attachment

internal typealias OnConversationAttachmentClick = (
    contentType: String,
    contentUri: String,
    partId: String,
) -> Unit
