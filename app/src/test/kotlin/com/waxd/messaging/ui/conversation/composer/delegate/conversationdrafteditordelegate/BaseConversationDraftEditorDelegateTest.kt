package com.waxd.messaging.ui.conversation.composer.delegate.conversationdrafteditordelegate

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftAttachment
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftPendingAttachment
import com.waxd.messaging.data.conversation.model.draft.ConversationDraftPendingAttachmentKind
import com.waxd.messaging.data.subscription.repository.SubscriptionsRepository
import com.waxd.messaging.domain.conversation.usecase.draft.ResolveConversationDraftSendProtocol
import com.waxd.messaging.domain.conversation.usecase.draft.ResolveDraftAttachmentsWithinLimit
import com.waxd.messaging.domain.conversation.usecase.draft.model.ConversationDraftSendProtocol
import com.waxd.messaging.domain.conversation.usecase.draft.model.DraftAttachmentLimitResult
import com.waxd.messaging.testutil.MainDispatcherRule
import com.waxd.messaging.testutil.TEST_CONVERSATION_ID as CONVERSATION_ID
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationDraftEditorDelegateImpl
import com.waxd.messaging.ui.conversation.composer.delegate.PersistedDraftUpdate
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
internal abstract class BaseConversationDraftEditorDelegateTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    protected val subscriptionsRepository = mockk<SubscriptionsRepository>()
    protected val resolveConversationDraftSendProtocol =
        mockk<ResolveConversationDraftSendProtocol>()
    protected val resolveDraftAttachmentsWithinLimit = mockk<ResolveDraftAttachmentsWithinLimit>()

    @Before
    fun setUpDefaultCollaboratorStubs() {
        every { subscriptionsRepository.resolveAttachmentLimit() } returns DEFAULT_ATTACHMENT_LIMIT
        coEvery {
            resolveConversationDraftSendProtocol(conversationId = any(), draft = any())
        } returns ConversationDraftSendProtocol.SMS
    }

    protected fun createDelegate(): ConversationDraftEditorDelegateImpl {
        return ConversationDraftEditorDelegateImpl(
            subscriptionsRepository = subscriptionsRepository,
            resolveConversationDraftSendProtocol = resolveConversationDraftSendProtocol,
            resolveDraftAttachmentsWithinLimit = resolveDraftAttachmentsWithinLimit,
        )
    }

    protected fun loadedDelegate(
        conversationId: ConversationId = CONVERSATION_ID,
        persistedDraft: ConversationDraft = draft(),
    ): ConversationDraftEditorDelegateImpl {
        return createDelegate().also { delegate ->
            delegate.reset(conversationId = conversationId)
            delegate.applyPersistedDraftUpdate(
                persistedDraftUpdate = PersistedDraftUpdate(
                    conversationId = conversationId,
                    persistedDraft = persistedDraft,
                ),
            )
        }
    }

    protected fun persistedDraftUpdate(
        conversationId: ConversationId = CONVERSATION_ID,
        persistedDraft: ConversationDraft = draft(),
    ): PersistedDraftUpdate {
        return PersistedDraftUpdate(
            conversationId = conversationId,
            persistedDraft = persistedDraft,
        )
    }

    @Suppress("SameParameterValue")
    protected fun givenAttachmentLimit(limit: Int) {
        every { subscriptionsRepository.resolveAttachmentLimit() } returns limit
    }

    protected fun givenResolvedSendProtocol(protocol: ConversationDraftSendProtocol) {
        coEvery {
            resolveConversationDraftSendProtocol(conversationId = any(), draft = any())
        } returns protocol
    }

    protected fun givenAttachmentLimitResult(
        attachmentsToAdd: List<ConversationDraftAttachment>,
        didDropAttachments: Boolean,
    ) {
        every {
            resolveDraftAttachmentsWithinLimit(
                currentAttachments = any(),
                attachmentsToAdd = any(),
            )
        } returns DraftAttachmentLimitResult(
            attachmentsToAdd = attachmentsToAdd,
            didDropAttachments = didDropAttachments,
        )
    }

    protected fun draft(
        messageText: String = "",
        subjectText: String = "",
        selfParticipantId: String = "",
        attachments: List<ConversationDraftAttachment> = emptyList(),
    ): ConversationDraft {
        return ConversationDraft(
            messageText = messageText,
            subjectText = subjectText,
            selfParticipantId = ParticipantId(selfParticipantId),
            attachments = attachments.toImmutableList(),
        )
    }

    protected fun attachment(
        contentUri: String,
        contentType: String = "image/jpeg",
        captionText: String = "",
    ): ConversationDraftAttachment {
        return ConversationDraftAttachment(
            contentType = contentType,
            contentUri = contentUri,
            captionText = captionText,
        )
    }

    protected fun pendingAttachment(
        pendingAttachmentId: String,
        contentUri: String = "content://pending/$pendingAttachmentId",
        contentType: String = "image/jpeg",
        kind: ConversationDraftPendingAttachmentKind =
            ConversationDraftPendingAttachmentKind.Generic,
    ): ConversationDraftPendingAttachment {
        return ConversationDraftPendingAttachment(
            pendingAttachmentId = pendingAttachmentId,
            contentUri = contentUri,
            contentType = contentType,
            kind = kind,
        )
    }

    protected companion object {
        const val DEFAULT_ATTACHMENT_LIMIT = 10
    }
}
