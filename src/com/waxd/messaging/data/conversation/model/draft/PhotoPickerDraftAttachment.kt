package com.waxd.messaging.data.conversation.model.draft

internal data class PhotoPickerDraftAttachment(
    val sourceContentUri: String,
    val draftAttachment: ConversationDraftAttachment,
)
