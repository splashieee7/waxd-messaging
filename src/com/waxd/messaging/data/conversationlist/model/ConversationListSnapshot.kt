package com.waxd.messaging.data.conversationlist.model

import com.waxd.messaging.data.conversation.model.ConversationId
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf

internal data class ConversationListSnapshot(
    val items: ImmutableList<ConversationListItem>,
    val blockedDestinations: ImmutableSet<String>,
    val hasFirstSyncCompleted: Boolean,
    val restoredConversationIds: ImmutableSet<ConversationId> = persistentSetOf(),
)
