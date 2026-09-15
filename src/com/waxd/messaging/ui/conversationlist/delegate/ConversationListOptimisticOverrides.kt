package com.waxd.messaging.ui.conversationlist.delegate

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversationlist.model.ConversationListItem
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf

internal data class ConversationListOptimisticOverrides(
    val removalById: PersistentMap<ConversationId, ConversationRemovalOverride> = persistentMapOf(),
    val readById: PersistentMap<ConversationId, Boolean> = persistentMapOf(),
    val pinnedById: PersistentMap<ConversationId, Boolean> = persistentMapOf(),
) {
    val isEmpty: Boolean
        get() = removalById.isEmpty() &&
            readById.isEmpty() &&
            pinnedById.isEmpty()
}

internal data class ConversationRemovalOverride(
    val item: ConversationListItem,
    val kind: Kind,
    val awaitingRemoval: Boolean,
) {
    enum class Kind {
        Removed,
        Restored,
    }
}
