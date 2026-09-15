package com.waxd.messaging.testutil

import android.app.NotificationChannel
import android.app.NotificationManager
import com.waxd.messaging.util.NotificationChannelUtil

internal fun createIncomingMessagesTestChannel(
    importance: Int = NotificationManager.IMPORTANCE_HIGH,
) {
    NotificationChannelUtil.getNotificationManager().createNotificationChannel(
        NotificationChannel(
            NotificationChannelUtil.INCOMING_MESSAGES,
            "Incoming messages",
            importance,
        ),
    )
}
