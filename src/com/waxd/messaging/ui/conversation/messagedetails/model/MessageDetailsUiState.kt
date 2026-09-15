package com.waxd.messaging.ui.conversation.messagedetails.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.conversation.model.message.ConversationMessageDetails
import com.waxd.messaging.ui.conversation.messages.model.message.ConversationMessageUiModel

@Immutable
internal sealed interface MessageDetailsUiState {

    data object Loading : MessageDetailsUiState

    data object Unavailable : MessageDetailsUiState

    @Immutable
    data class Content(
        val preview: ConversationMessageUiModel,
        val details: ConversationMessageDetails,
        val youTubeLinkPreviewsEnabled: Boolean = false,
    ) : MessageDetailsUiState
}
