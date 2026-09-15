package com.waxd.messaging.ui.conversationsettings.screen.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface ConversationSettingsNavEvent {

    data class OpenParticipantChat(
        val conversationId: ConversationId,
    ) : ConversationSettingsNavEvent

    data class OpenParticipantInfo(
        val conversationId: ConversationId,
    ) : ConversationSettingsNavEvent

    data object CloseAfterArchive : ConversationSettingsNavEvent
}
