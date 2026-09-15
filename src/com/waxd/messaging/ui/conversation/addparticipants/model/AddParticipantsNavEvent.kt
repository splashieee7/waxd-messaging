package com.waxd.messaging.ui.conversation.addparticipants.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface AddParticipantsNavEvent {

    data class OpenConversation(
        val conversationId: ConversationId,
    ) : AddParticipantsNavEvent
}
