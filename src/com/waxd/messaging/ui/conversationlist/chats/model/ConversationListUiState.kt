package com.waxd.messaging.ui.conversationlist.chats.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.ui.conversationlist.model.ConversationListContentUiState

@Immutable
internal data class ConversationListUiState(
    val content: ConversationListContentUiState = ConversationListContentUiState.Loading,
    val selection: ConversationListSelectionUiState = ConversationListSelectionUiState(),
    val isScrollToTopVisible: Boolean = false,
    val hasBlockedParticipants: Boolean = false,
    val isDebugEnabled: Boolean = false,
)
