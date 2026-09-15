package com.waxd.messaging.ui.debug.screen.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.subscription.model.SubId

@Immutable
internal data class DebugSimUiState(
    val subId: SubId,
    val mccMnc: String,
)
