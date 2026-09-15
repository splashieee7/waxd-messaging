package com.waxd.messaging.ui.conversation.composer.model

import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftPendingAttachment
import com.waxd.messaging.domain.conversation.usecase.draft.model.ConversationDraftSendProtocol

internal data class ConversationDraftState(
    val draft: ConversationDraft = ConversationDraft(),
    val pendingAttachments: List<ConversationDraftPendingAttachment> = emptyList(),
    val sendProtocol: ConversationDraftSendProtocol = ConversationDraftSendProtocol.SMS,
)
