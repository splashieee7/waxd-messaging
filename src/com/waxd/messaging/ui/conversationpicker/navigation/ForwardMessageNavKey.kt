package com.waxd.messaging.ui.conversationpicker.navigation

import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.MessageId
import kotlinx.serialization.Serializable

@Serializable
internal data class ForwardMessageNavKey(
    val conversationId: ConversationId,
    val messageId: MessageId,
) : NavKey
