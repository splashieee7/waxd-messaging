package com.waxd.messaging.ui.conversation.composer.delegate.drafteditorstate

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftAttachment
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftPendingAttachment
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftPendingAttachmentKind
import com.waxd.messaging.ui.conversation.composer.delegate.DraftEditorState
import kotlinx.collections.immutable.toImmutableList

internal abstract class BaseDraftEditorStateTest {

    protected fun draft(
        messageText: String = "",
        subjectText: String = "",
        selfParticipantId: String? = null,
        attachments: List<ConversationDraftAttachment> = emptyList(),
        isCheckingDraft: Boolean = false,
        isSending: Boolean = false,
    ): ConversationDraft {
        return ConversationDraft(
            messageText = messageText,
            subjectText = subjectText,
            selfParticipantId = ParticipantId.fromOrNull(selfParticipantId),
            attachments = attachments.toImmutableList(),
            isCheckingDraft = isCheckingDraft,
            isSending = isSending,
        )
    }

    protected fun attachment(
        contentUri: String,
        contentType: String = "image/jpeg",
        captionText: String = "",
        width: Int? = null,
        height: Int? = null,
        durationMillis: Long? = null,
    ): ConversationDraftAttachment {
        return ConversationDraftAttachment(
            contentType = contentType,
            contentUri = contentUri,
            captionText = captionText,
            width = width,
            height = height,
            durationMillis = durationMillis,
        )
    }

    protected fun pendingAttachment(
        pendingAttachmentId: String,
        contentUri: String = "content://pending/$pendingAttachmentId",
        contentType: String = "image/jpeg",
        displayName: String = "",
        kind: ConversationDraftPendingAttachmentKind =
            ConversationDraftPendingAttachmentKind.Generic,
    ): ConversationDraftPendingAttachment {
        return ConversationDraftPendingAttachment(
            pendingAttachmentId = pendingAttachmentId,
            contentUri = contentUri,
            contentType = contentType,
            displayName = displayName,
            kind = kind,
        )
    }

    protected fun loadedState(
        conversationId: ConversationId? = CONVERSATION_ID,
        persistedDraft: ConversationDraft = draft(),
        isSending: Boolean = false,
        pendingAttachments: List<ConversationDraftPendingAttachment> = emptyList(),
        pendingSentDraft: ConversationDraft? = null,
    ): DraftEditorState {
        return DraftEditorState(
            conversationId = conversationId,
            persistedDraft = persistedDraft,
            isLoaded = true,
            isSending = isSending,
            pendingAttachments = pendingAttachments,
            pendingSentDraft = pendingSentDraft,
        )
    }

    protected companion object {
        val CONVERSATION_ID = ConversationId("conversation-1")
    }
}
