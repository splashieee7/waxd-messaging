package com.waxd.messaging.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.waxd.messaging.util.BugleActivityUtil
import com.waxd.messaging.util.UiUtils

open class BugleComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UiUtils.redirectToOnboardingIfNeeded(this)
    }

    override fun onResume() {
        super.onResume()
        BugleActivityUtil.onActivityResume(this, this)
    }
}
