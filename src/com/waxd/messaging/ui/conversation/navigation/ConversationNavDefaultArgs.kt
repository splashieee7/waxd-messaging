package com.waxd.messaging.ui.conversation.navigation

import android.os.Bundle
import com.waxd.messaging.ui.conversation.messagedetails.MESSAGE_DETAILS_CONVERSATION_ID_ARG
import com.waxd.messaging.ui.conversation.messagedetails.MESSAGE_DETAILS_MESSAGE_ID_ARG

internal fun messageDetailsDefaultArgs(navKey: MessageDetailsNavKey): Bundle {
    return Bundle().apply {
        putString(MESSAGE_DETAILS_CONVERSATION_ID_ARG, navKey.conversationId.value)
        putString(MESSAGE_DETAILS_MESSAGE_ID_ARG, navKey.messageId.value)
    }
}
