package com.waxd.messaging.ui.conversation.recipientpicker.model.picker

internal sealed interface ConversationResolutionState {
    data object Idle : ConversationResolutionState

    data class Resolving(
        val recipientDestination: String?,
        val isIndicatorVisible: Boolean,
    ) : ConversationResolutionState
}
