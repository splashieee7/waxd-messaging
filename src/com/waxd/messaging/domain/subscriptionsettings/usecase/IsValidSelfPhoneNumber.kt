package com.waxd.messaging.domain.subscriptionsettings.usecase

import com.waxd.messaging.data.subscription.model.SubId
import com.waxd.messaging.util.PhoneUtils
import javax.inject.Inject

internal fun interface IsValidSelfPhoneNumber {
    operator fun invoke(subId: SubId, phoneNumber: String): Boolean
}

internal class IsValidSelfPhoneNumberImpl @Inject constructor() : IsValidSelfPhoneNumber {

    override fun invoke(subId: SubId, phoneNumber: String): Boolean {
        return phoneNumber.isEmpty() ||
            PhoneUtils
                .get(subId.value)
                .getValidSelfE164Number(phoneNumber) != null
    }
}
