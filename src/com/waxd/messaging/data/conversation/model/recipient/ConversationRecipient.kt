package com.waxd.messaging.data.conversation.model.recipient

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.conversation.model.ParticipantId

@Immutable
internal data class ConversationRecipient(
    val id: ParticipantId,
    val displayName: String,
    val destination: String,
    val photoUri: String? = null,
    val secondaryText: String? = null,
)
