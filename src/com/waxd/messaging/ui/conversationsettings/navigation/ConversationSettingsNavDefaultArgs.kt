package com.waxd.messaging.ui.conversationsettings.navigation

import android.os.Bundle
import com.waxd.messaging.ui.conversationsettings.screen.CONVERSATION_SETTINGS_CONVERSATION_ID_ARG

internal fun conversationSettingsDefaultArgs(navKey: ConversationSettingsNavKey): Bundle {
    return Bundle().apply {
        putString(CONVERSATION_SETTINGS_CONVERSATION_ID_ARG, navKey.conversationId.value)
    }
}
