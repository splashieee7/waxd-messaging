package com.waxd.messaging.ui.conversationpicker

import com.waxd.messaging.ui.conversationpicker.model.ConversationPickerEffect as Effect

internal interface ConversationPickerEffectHandler {
    fun handle(effect: Effect)
}
