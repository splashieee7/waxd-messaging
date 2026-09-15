package com.waxd.messaging.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.waxd.messaging.BugleApplication
import com.waxd.messaging.Factory
import com.waxd.messaging.datamodel.action.UpdateMessageNotificationAction
import com.waxd.messaging.util.BuglePrefsKeys
import com.waxd.messaging.util.LogUtil

class BootAndPackageReplacedReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED &&
            intent.action != Intent.ACTION_MY_PACKAGE_REPLACED
        ) {
            LogUtil.i(
                LogUtil.BUGLE_TAG,
                "BootAndPackageReplacedReceiver got unexpected action: ${intent.action}",
            )
            return
        }

        // Repost unseen notifications
        Factory.get().applicationPrefs.putLong(
            BuglePrefsKeys.LATEST_NOTIFICATION_MESSAGE_TIMESTAMP,
            Long.MIN_VALUE,
        )

        UpdateMessageNotificationAction.updateMessageNotification()
        BugleApplication.updateAppConfig(context)
    }
}
