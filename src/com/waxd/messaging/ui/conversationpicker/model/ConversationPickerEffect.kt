package com.waxd.messaging.ui.conversationpicker.model

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.draft.ConversationDraft
import com.waxd.messaging.domain.conversationpicker.model.SendTarget
import kotlinx.collections.immutable.ImmutableSet

internal sealed interface ConversationPickerEffect {

    data object OpenConversationFailed : ConversationPickerEffect

    data class OpenConversation(
        val conversationId: ConversationId,
    ) : ConversationPickerEffect

    data class SendToSelected(
        val targets: ImmutableSet<SendTarget>,
        val draft: ConversationDraft,
    ) : ConversationPickerEffect

    data class OpenAttachmentPreview(
        val contentUri: String,
        val contentType: String,
    ) : ConversationPickerEffect
}
