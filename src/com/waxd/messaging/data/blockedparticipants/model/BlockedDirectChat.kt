package com.waxd.messaging.data.blockedparticipants.model

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.datamodel.data.ParticipantData

internal data class BlockedDirectChat(
    val participant: ParticipantData,
    val conversationId: ConversationId,
)
