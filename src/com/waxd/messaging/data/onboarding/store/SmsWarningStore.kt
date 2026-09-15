package com.waxd.messaging.data.onboarding.store

import com.waxd.messaging.util.BuglePrefs
import com.waxd.messaging.util.BuglePrefsKeys
import javax.inject.Inject

internal interface SmsWarningStore {
    fun isAcknowledged(): Boolean
    fun acknowledge()
}

internal class SmsWarningStoreImpl @Inject constructor() : SmsWarningStore {

    override fun isAcknowledged(): Boolean {
        return BuglePrefs.getApplicationPrefs().getBoolean(
            BuglePrefsKeys.SMS_WARNING_ACKNOWLEDGED,
            BuglePrefsKeys.SMS_WARNING_ACKNOWLEDGED_DEFAULT,
        )
    }

    override fun acknowledge() {
        BuglePrefs.getApplicationPrefs().putBoolean(
            BuglePrefsKeys.SMS_WARNING_ACKNOWLEDGED,
            true,
        )
    }
}
