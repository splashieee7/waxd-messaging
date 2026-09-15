package com.waxd.messaging.ui.conversationpicker.host.share

import android.app.Activity
import com.waxd.messaging.R
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.datamodel.data.MessageData
import com.waxd.messaging.domain.conversationpicker.model.SendContentResult
import com.waxd.messaging.domain.conversationpicker.model.SendTarget
import com.waxd.messaging.domain.conversationpicker.usecase.BuildMessageDataFromDraft
import com.waxd.messaging.domain.conversationpicker.usecase.SendContentToTargets
import com.waxd.messaging.ui.UIIntents
import com.waxd.messaging.ui.common.components.attachment.openAttachmentPreview
import com.waxd.messaging.ui.conversationpicker.ConversationPickerEffectHandler
import com.waxd.messaging.ui.conversationpicker.model.ConversationPickerEffect as Effect
import com.waxd.messaging.util.UiUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ShareIntentEffectHandler(
    private val applicationScope: CoroutineScope,
    private val mainDispatcher: CoroutineDispatcher,
    private val activity: Activity,
    private val draft: ConversationDraft?,
    private val sendContentToTargets: SendContentToTargets,
    private val buildMessageDataFromDraft: BuildMessageDataFromDraft,
) : ConversationPickerEffectHandler {

    private val messageData: MessageData? by lazy {
        draft?.let(buildMessageDataFromDraft::invoke)
    }

    override fun handle(effect: Effect) {
        when (effect) {
            is Effect.OpenConversation -> {
                openConversation(effect.conversationId)
            }

            is Effect.OpenConversationFailed -> {
                UiUtils.showToastAtBottom(R.string.conversation_picker_open_failed)
            }

            is Effect.SendToSelected -> {
                sendToSelected(effect.targets, effect.draft)
            }

            is Effect.OpenAttachmentPreview -> {
                openPreview(effect.contentUri, effect.contentType)
            }
        }
    }

    fun showNoShareableContentNotice() {
        UiUtils.showToastAtBottom(R.string.share_no_shareable_content)
    }

    fun showDroppedContentNotice() {
        UiUtils.showToastAtBottom(R.string.share_dropped_content)
    }

    private fun openPreview(
        contentUri: String,
        contentType: String,
    ) {
        applicationScope.launch(mainDispatcher) {
            openAttachmentPreview(
                context = activity,
                contentUri = contentUri,
                contentType = contentType,
            )
        }
    }

    private fun openConversation(conversationId: ConversationId) {
        UIIntents.get().launchConversationActivity(activity, conversationId.value, messageData)
        activity.finish()
    }

    private fun sendToSelected(
        targets: Set<SendTarget>,
        draft: ConversationDraft,
    ) {
        applicationScope.launch {
            val result = sendContentToTargets(draft, targets)
            if (result is SendContentResult.Failure) {
                withContext(mainDispatcher) {
                    UiUtils.showToastAtBottom(R.string.send_message_failure)
                }
            }
        }

        UIIntents.get().launchConversationListActivity(activity)
        activity.finish()
    }
}
