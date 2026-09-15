package com.waxd.messaging.ui.conversation.mediapicker.mapper

import com.waxd.messaging.data.conversation.model.draft.ConversationDraftAttachment
import com.waxd.messaging.data.media.model.ConversationCapturedMedia
import org.junit.Assert.assertEquals
import org.junit.Test

class ConversationDraftAttachmentMapperImplTest {

    private val mapper = ConversationDraftAttachmentMapperImpl()

    @Test
    fun map_capturedMedia_returnsDraftAttachment() {
        val capturedMedia = ConversationCapturedMedia(
            contentUri = "content://scratch/1",
            contentType = "video/mp4",
            width = 1920,
            height = 1080,
        )

        val attachment = mapper.map(
            capturedMedia = capturedMedia,
        )

        assertEquals(
            ConversationDraftAttachment(
                contentType = "video/mp4",
                contentUri = "content://scratch/1",
                width = 1920,
                height = 1080,
            ),
            attachment,
        )
    }
}
