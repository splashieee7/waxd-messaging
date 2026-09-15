package com.waxd.messaging.data.subscription

import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.data.subscription.model.SubId
import com.waxd.messaging.data.subscription.model.Subscription
import com.waxd.messaging.datamodel.data.ParticipantData
import kotlinx.collections.immutable.ImmutableList

internal fun resolveSelectedSubscription(
    subscriptions: ImmutableList<Subscription>,
    selectedSelfParticipantId: ParticipantId?,
    defaultSmsSubscriptionId: SubId,
): Subscription? {
    subscriptions
        .firstOrNull { subscription ->
            subscription.selfParticipantId == selectedSelfParticipantId
        }
        ?.let { return it }

    val defaultSelection = when {
        defaultSmsSubscriptionId.value == ParticipantData.DEFAULT_SELF_SUB_ID -> null
        else -> {
            subscriptions.firstOrNull { subscription ->
                subscription.subId == defaultSmsSubscriptionId
            }
        }
    }

    return defaultSelection ?: subscriptions.firstOrNull()
}
