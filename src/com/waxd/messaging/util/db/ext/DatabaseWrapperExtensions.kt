package com.waxd.messaging.util.db.ext

import com.waxd.messaging.datamodel.DatabaseWrapper

inline fun DatabaseWrapper.withTransaction(block: () -> Unit) {
    beginTransaction()
    try {
        block()
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}
