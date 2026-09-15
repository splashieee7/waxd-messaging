package com.waxd.messaging.ui.conversationlist.common.pane

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.waxd.messaging.ui.common.components.LocalIsListDetailPane

@Composable
internal fun listPaneContentColor(): Color {
    return when {
        LocalIsListDetailPane.current -> MaterialTheme.colorScheme.surfaceContainerLow
        else -> MaterialTheme.colorScheme.background
    }
}
