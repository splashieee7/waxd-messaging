package com.waxd.messaging.data.conversation.model.message

import com.waxd.messaging.datamodel.data.ConversationMessageData
import com.waxd.messaging.datamodel.data.ConversationParticipantsData
import com.waxd.messaging.datamodel.data.ParticipantData

internal data class ConversationMessageDetailsData(
    val message: ConversationMessageData,
    val participants: ConversationParticipantsData,
    val selfParticipant: ParticipantData?,
)
