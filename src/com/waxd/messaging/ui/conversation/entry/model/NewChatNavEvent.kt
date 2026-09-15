package com.waxd.messaging.ui.conversation.entry.model

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId

internal sealed interface NewChatNavEvent {

    data object Close : NewChatNavEvent

    data class OpenConversation(
        val conversationId: ConversationId,
        val selfParticipantId: ParticipantId?,
    ) : NewChatNavEvent
}
