package com.waxd.messaging.ui.core

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(size = 12.dp),
    small = RoundedCornerShape(size = 16.dp),
    medium = RoundedCornerShape(size = 20.dp),
    large = RoundedCornerShape(size = 28.dp),
    extraLarge = RoundedCornerShape(size = 36.dp),
)

// Waxd terminal/ops-console theme -- same four hex values as every other waxd
// reskin. Like waxd-pdfviewer, this app has no config flag gating dynamic
// color, so the dynamicDarkColorScheme/dynamicLightColorScheme calls are
// replaced outright with this fixed scheme, applied regardless of system
// light/dark to match the rest of the suite always rendering dark.
// primaryContainer/onPrimaryContainer drive the outgoing message bubble
// (see messageBubbleColor/messageBubbleContentColor in
// ConversationMessageBubble.kt) -- a lighter, legible waxd tone rather than
// pure background, so sent bubbles stay visually distinct from the screen.
private val WaxdBackground = Color(0xFF0A0C0A)
private val WaxdSurfaceHigh = Color(0xFF16201A)
private val WaxdText = Color(0xFFD8F5E0)
private val WaxdPrimary = Color(0xFF2E9E5B)
private val WaxdAccent = Color(0xFF4CFF8B)

private val WaxdColorScheme = darkColorScheme(
    primary = WaxdPrimary,
    onPrimary = WaxdBackground,
    primaryContainer = WaxdPrimary,
    onPrimaryContainer = WaxdBackground,
    secondary = WaxdAccent,
    onSecondary = WaxdBackground,
    background = WaxdBackground,
    onBackground = WaxdText,
    surface = WaxdBackground,
    onSurface = WaxdText,
    surfaceContainerHigh = WaxdSurfaceHigh,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = WaxdColorScheme,
        shapes = AppShapes,
        content = content,
    )
}
