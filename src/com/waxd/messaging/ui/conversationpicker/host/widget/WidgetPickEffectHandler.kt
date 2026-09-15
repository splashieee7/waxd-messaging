package com.waxd.messaging.ui.conversationpicker.host.widget

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.appwidget.AppWidgetManager
import android.content.Intent
import com.waxd.messaging.R
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.ui.conversationpicker.ConversationPickerEffectHandler
import com.waxd.messaging.ui.conversationpicker.model.ConversationPickerEffect as Effect
import com.waxd.messaging.util.UiUtils
import com.waxd.messaging.widget.WidgetConversationPrefs
import com.waxd.messaging.widget.WidgetConversationProvider

internal class WidgetPickEffectHandler(
    private val activity: Activity,
    private val appWidgetId: Int,
) : ConversationPickerEffectHandler {

    override fun handle(effect: Effect) {
        when (effect) {
            is Effect.OpenConversation -> {
                bindConversationToWidget(effect.conversationId)
            }

            is Effect.OpenConversationFailed -> {
                UiUtils.showToastAtBottom(R.string.conversation_picker_open_failed)
            }

            is Effect.SendToSelected -> Unit
            is Effect.OpenAttachmentPreview -> Unit
        }
    }

    private fun bindConversationToWidget(conversationId: ConversationId) {
        WidgetConversationPrefs.saveConversationIdPref(appWidgetId, conversationId.value)
        WidgetConversationProvider.rebuildWidget(activity, appWidgetId)

        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        activity.setResult(RESULT_OK, resultValue)
        activity.finish()
    }
}
