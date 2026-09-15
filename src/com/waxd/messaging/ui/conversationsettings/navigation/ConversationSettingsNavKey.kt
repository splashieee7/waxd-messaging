package com.waxd.messaging.ui.conversationsettings.navigation

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.ui.navigation.ConversationScopedNavKey
import kotlinx.serialization.Serializable

@Serializable
internal data class ConversationSettingsNavKey(
    override val conversationId: ConversationId,
) : ConversationScopedNavKey
