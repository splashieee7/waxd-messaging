package com.waxd.messaging.domain.subscriptionsettings.usecase

import android.content.Context
import com.waxd.messaging.R
import com.waxd.messaging.data.subscription.model.SubId
import com.waxd.messaging.datamodel.ParticipantRefresh
import com.waxd.messaging.di.core.IoDispatcher
import com.waxd.messaging.util.BuglePrefs
import com.waxd.messaging.util.LogUtil
import com.waxd.messaging.util.PhoneUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal fun interface SetSubscriptionPhoneNumber {
    suspend operator fun invoke(subId: SubId, phoneNumber: String)
}

internal class SetSubscriptionPhoneNumberImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SetSubscriptionPhoneNumber {

    override suspend fun invoke(subId: SubId, phoneNumber: String) {
        withContext(ioDispatcher) {
            val phoneUtils = PhoneUtils.get(subId.value)
            val key = context.getString(R.string.mms_phone_number_pref_key)
            val subPrefs = BuglePrefs.getSubscriptionPrefs(subId.value)

            val e164Number = phoneNumber
                .takeIf(String::isNotEmpty)
                ?.let(phoneUtils::getValidSelfE164Number)

            when {
                phoneNumber.isEmpty() -> subPrefs.remove(key)

                // The dialog rejects these before they get here. Guarded again because whatever
                // is stored becomes the sender identity of every outgoing MMS.
                e164Number == null -> {
                    LogUtil.w(LogUtil.BUGLE_TAG, "SetSubscriptionPhoneNumber: not a phone number")
                    return@withContext
                }

                e164Number == phoneUtils.getCanonicalForSelf(false) -> subPrefs.remove(key)

                else -> subPrefs.putString(key, e164Number)
            }

            ParticipantRefresh.refreshSelfParticipants()
        }
    }
}
