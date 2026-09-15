package com.waxd.messaging.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.data.conversation.model.ConversationId

internal interface ConversationScopedNavKey : NavKey {
    val conversationId: ConversationId
}
