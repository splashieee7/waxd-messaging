package com.waxd.messaging.ui.conversation.composer.model

import com.waxd.messaging.data.subscription.model.SubId
import com.waxd.messaging.data.subscription.model.Subscription
import kotlinx.collections.immutable.ImmutableList

internal data class ConversationSubscriptionSelectionState(
    val subscriptions: ImmutableList<Subscription>,
    val areSubscriptionsLoaded: Boolean,
    val defaultSmsSubscriptionId: SubId,
)
