package com.waxd.messaging.ui.conversation.navigation

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.datamodel.data.MessageData

internal interface ConversationDraftLauncher {
    fun launch(
        conversationId: ConversationId,
        draft: MessageData?,
    )
}
