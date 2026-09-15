package com.waxd.messaging.data.conversationsettings.model

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.datamodel.data.ParticipantData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class ConversationSettingsData(
    val conversationId: ConversationId,
    val conversationTitle: String = "",
    val isArchived: Boolean = false,
    val isSnoozed: Boolean = false,
    val participants: ImmutableList<ParticipantData> = persistentListOf(),
    val dbSelfParticipantId: ParticipantId? = null,
)
