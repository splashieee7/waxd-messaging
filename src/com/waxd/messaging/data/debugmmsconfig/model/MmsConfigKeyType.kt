package com.waxd.messaging.data.debugmmsconfig.model

import com.waxd.messaging.sms.MmsConfig

internal enum class MmsConfigKeyType(
    val rawType: String,
) {
    BOOL(MmsConfig.KEY_TYPE_BOOL),
    INT(MmsConfig.KEY_TYPE_INT),
    STRING(MmsConfig.KEY_TYPE_STRING),
    ;

    companion object {
        fun fromRawType(rawType: String?): MmsConfigKeyType? {
            return entries.firstOrNull { keyType ->
                keyType.rawType == rawType
            }
        }
    }
}
