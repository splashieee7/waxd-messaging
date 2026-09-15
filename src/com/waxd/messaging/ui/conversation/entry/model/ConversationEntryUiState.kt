package com.waxd.messaging.ui.conversation.entry.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.conversation.model.draft.ConversationDraft

@Immutable
internal data class ConversationEntryUiState(
    val conversationId: ConversationId? = null,
    val pendingDraft: ConversationDraft? = null,
    val pendingScrollPosition: Int? = null,
    val pendingSelfParticipantId: ParticipantId? = null,
    val pendingStartupAttachment: ConversationEntryStartupAttachment? = null,
)

@Immutable
internal data class ConversationEntryStartupAttachment(
    val contentType: String,
    val contentUri: String,
)
