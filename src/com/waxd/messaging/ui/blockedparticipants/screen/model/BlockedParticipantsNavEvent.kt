package com.waxd.messaging.ui.blockedparticipants.screen.model

import com.waxd.messaging.data.conversation.model.ConversationId

internal sealed interface BlockedParticipantsNavEvent {

    data object CloseAfterLastUnblock : BlockedParticipantsNavEvent

    data class OpenParticipantChat(
        val conversationId: ConversationId,
    ) : BlockedParticipantsNavEvent
}
