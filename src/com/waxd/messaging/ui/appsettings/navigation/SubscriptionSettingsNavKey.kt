package com.waxd.messaging.ui.appsettings.navigation

import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.data.subscription.model.SubId
import kotlinx.serialization.Serializable

@Serializable
internal data class SubscriptionSettingsNavKey(
    val subId: SubId,
    val title: String,
) : NavKey
