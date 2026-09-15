package com.waxd.messaging.ui.conversationpicker.host.forward

import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.datamodel.data.MessageData

internal data class ForwardMessageUiState(
    val draft: ConversationDraft? = null,
    val message: MessageData? = null,
    val isLoading: Boolean = true,
)
