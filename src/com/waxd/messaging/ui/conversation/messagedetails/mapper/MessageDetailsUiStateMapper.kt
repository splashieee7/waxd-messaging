package com.waxd.messaging.ui.conversation.messagedetails.mapper

import com.waxd.messaging.data.conversation.model.message.ConversationMessageDetails
import com.waxd.messaging.datamodel.data.ConversationMessageData
import com.waxd.messaging.ui.conversation.messagedetails.model.MessageDetailsUiState
import com.waxd.messaging.ui.conversation.messages.mapper.ConversationMessageUiModelMapper
import javax.inject.Inject

internal interface MessageDetailsUiStateMapper {
    fun map(
        message: ConversationMessageData?,
        details: ConversationMessageDetails?,
        youTubeLinkPreviewsEnabled: Boolean,
    ): MessageDetailsUiState
}

internal class MessageDetailsUiStateMapperImpl @Inject constructor(
    private val conversationMessageUiModelMapper: ConversationMessageUiModelMapper,
) : MessageDetailsUiStateMapper {

    override fun map(
        message: ConversationMessageData?,
        details: ConversationMessageDetails?,
        youTubeLinkPreviewsEnabled: Boolean,
    ): MessageDetailsUiState {
        val preview = message?.let(conversationMessageUiModelMapper::map)

        if (preview == null || details == null) {
            return MessageDetailsUiState.Unavailable
        }

        return MessageDetailsUiState.Content(
            preview = preview,
            details = details,
            youTubeLinkPreviewsEnabled = youTubeLinkPreviewsEnabled,
        )
    }
}
