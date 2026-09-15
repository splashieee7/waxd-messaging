package com.waxd.messaging.ui.appsettings.subscription.model

internal sealed interface SubscriptionSettingsNavEvent {
    data object Close : SubscriptionSettingsNavEvent
}
