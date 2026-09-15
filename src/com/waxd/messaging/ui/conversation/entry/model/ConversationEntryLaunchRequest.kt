package com.waxd.messaging.ui.conversation.entry.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.datamodel.data.MessageData

@Immutable
internal data class ConversationEntryLaunchRequest(
    val conversationId: ConversationId?,
    val draftData: MessageData? = null,
    val startupAttachmentUri: String? = null,
    val startupAttachmentType: String? = null,
    val messagePosition: Int? = null,
)
