package com.waxd.messaging.ui.conversation.messages.ui.attachment

import androidx.compose.ui.test.junit4.v2.createComposeRule
import com.waxd.messaging.ui.core.AppTheme
import org.junit.Rule

internal abstract class BaseConversationInlineAudioAttachmentRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    protected fun setContent(
        isPlaying: Boolean,
        progress: Float,
    ) {
        setContent(
            isPlaying = { isPlaying },
            progress = { progress },
        )
    }

    protected fun setContent(
        isPlaying: () -> Boolean,
        progress: () -> Float,
        onClick: () -> Unit = {},
        onLongClick: () -> Unit = {},
    ) {
        composeTestRule.setContent {
            AppTheme {
                ConversationInlineAudioAttachmentRowContent(
                    colors = rememberConversationInlineAudioAttachmentColors(
                        isIncoming = true,
                        isSelectionMode = false,
                        useStandaloneAudioAttachmentBackground = false,
                    ),
                    isSelectionMode = false,
                    isPlaying = isPlaying(),
                    title = "Audio attachment",
                    durationLabel = "00:18",
                    progress = progress(),
                    onClick = onClick,
                    onLongClick = onLongClick,
                )
            }
        }
    }
}
