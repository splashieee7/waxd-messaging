package com.waxd.messaging.ui.conversation.recipientpicker.model.selection

import androidx.compose.runtime.Immutable
import com.waxd.messaging.ui.recipientselection.model.picker.SelectedRecipient
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal data class RecipientSelectionQueryFieldUiState(
    val query: String,
    val enabled: Boolean,
    val placeholderText: String,
    val selectedRecipients: ImmutableList<SelectedRecipient>,
)
