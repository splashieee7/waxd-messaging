package com.waxd.messaging.data.subscription.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.conversation.model.metadata.ConversationSubscriptionLabel

@Immutable
internal data class Subscription(
    val selfParticipantId: ParticipantId,
    val subId: SubId,
    val label: ConversationSubscriptionLabel,
    val displayDestination: String?,
    val displaySlotId: Int,
    val color: Int,
)
