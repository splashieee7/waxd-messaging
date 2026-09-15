package com.waxd.messaging.domain.conversation.usecase.draft.model

import com.waxd.messaging.data.conversation.model.draft.ConversationDraftAttachment

internal data class DraftAttachmentLimitResult(
    val attachmentsToAdd: List<ConversationDraftAttachment>,
    val didDropAttachments: Boolean,
)
