package com.waxd.messaging.data.debugmmsconfig.model

import com.waxd.messaging.data.subscription.model.SubId

internal data class DebugSim(
    val subId: SubId,
    val mcc: Int,
    val mnc: Int,
)
