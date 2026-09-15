package com.waxd.messaging.ui.conversationlist.navigation

import android.content.Intent
import com.waxd.messaging.ui.UIIntents

internal fun Intent.goToConversationList(): Boolean {
    return getBooleanExtra(UIIntents.UI_INTENT_EXTRA_GOTO_CONVERSATION_LIST, false)
}
