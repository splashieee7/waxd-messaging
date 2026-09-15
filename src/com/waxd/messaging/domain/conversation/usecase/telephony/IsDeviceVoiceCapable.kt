package com.waxd.messaging.domain.conversation.usecase.telephony

import com.waxd.messaging.util.PhoneUtils
import javax.inject.Inject

internal fun interface IsDeviceVoiceCapable {
    operator fun invoke(): Boolean
}

internal class IsDeviceVoiceCapableImpl @Inject constructor() : IsDeviceVoiceCapable {

    override operator fun invoke(): Boolean {
        return PhoneUtils.getDefault().isVoiceCapable
    }
}
