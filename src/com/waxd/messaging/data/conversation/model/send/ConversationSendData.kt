package com.waxd.messaging.data.conversation.model.send

import com.waxd.messaging.data.conversation.model.metadata.ConversationMetadata
import com.waxd.messaging.data.subscription.model.SubId
import com.waxd.messaging.datamodel.data.ConversationParticipantsData
import com.waxd.messaging.datamodel.data.ParticipantData

internal data class ConversationSendData(
    val metadata: ConversationMetadata,
    val participants: ConversationParticipantsData,
    val selfParticipant: ParticipantData?,
) {
    val selfSubId: SubId
        get() = SubId(selfParticipant?.subId ?: ParticipantData.DEFAULT_SELF_SUB_ID)
}
