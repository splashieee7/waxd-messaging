package com.waxd.messaging.domain.conversationpicker.usecase

import androidx.core.net.toUri
import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.datamodel.data.MessageData
import com.waxd.messaging.datamodel.data.PendingAttachmentData
import com.waxd.messaging.util.ContentType
import javax.inject.Inject

internal interface BuildMessageDataFromDraft {
    operator fun invoke(draft: ConversationDraft): MessageData
}

internal class BuildMessageDataFromDraftImpl @Inject constructor() : BuildMessageDataFromDraft {

    override fun invoke(draft: ConversationDraft): MessageData {
        return MessageData.createSharedMessage(
            draft.messageText,
            draft.subjectText,
        ).apply {
            draft.attachments
                .filter { attachment ->
                    ContentType.isMediaType(attachment.contentType)
                }
                .forEach { attachment ->
                    addPart(
                        PendingAttachmentData.createPendingAttachmentData(
                            attachment.contentType,
                            attachment.contentUri.toUri(),
                        ),
                    )
                }
        }
    }
}
