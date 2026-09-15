package com.waxd.messaging.ui.license

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.waxd.messaging.ui.core.AppTheme

class LicenseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AppTheme {
                LicenseScreen(
                    onNavigateBack = ::finish,
                )
            }
        }
    }
}
