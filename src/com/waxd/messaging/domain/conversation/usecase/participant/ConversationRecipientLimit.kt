package com.waxd.messaging.domain.conversation.usecase.participant

import com.waxd.messaging.datamodel.data.ParticipantData
import com.waxd.messaging.sms.MmsConfig

internal fun conversationRecipientLimit(): Int {
    return MmsConfig.get(ParticipantData.DEFAULT_SELF_SUB_ID).recipientLimit
}
