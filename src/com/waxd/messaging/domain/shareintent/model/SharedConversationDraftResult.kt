package com.waxd.messaging.domain.shareintent.model

import com.waxd.messaging.data.conversation.model.draft.ConversationDraft

internal data class SharedConversationDraftResult(
    val draft: ConversationDraft?,
    val hasDroppedContent: Boolean,
)
