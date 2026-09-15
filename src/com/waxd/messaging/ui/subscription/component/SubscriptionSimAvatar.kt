package com.waxd.messaging.ui.subscription.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.waxd.messaging.data.subscription.model.Subscription
import com.waxd.messaging.ui.subscription.mapper.toSimAccentColor

@Composable
internal fun SubscriptionSimAvatar(
    subscription: Subscription,
    modifier: Modifier = Modifier,
    size: Dp = SimAvatarDefaultSize,
) {
    SimAvatar(
        slotLabel = subscription.displaySlotId.toString(),
        accentColor = subscription.toSimAccentColor(),
        modifier = modifier,
        size = size,
    )
}
