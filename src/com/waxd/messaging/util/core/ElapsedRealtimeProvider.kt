package com.waxd.messaging.util.core

internal fun interface ElapsedRealtimeProvider {
    fun elapsedRealtimeMillis(): Long
}
