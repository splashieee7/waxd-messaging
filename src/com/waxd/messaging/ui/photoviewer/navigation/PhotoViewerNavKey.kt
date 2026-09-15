package com.waxd.messaging.ui.photoviewer.navigation

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.ui.navigation.ConversationScopedNavKey
import com.waxd.messaging.ui.photoviewer.model.PhotoViewerLaunchRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class PhotoViewerNavKey(
    override val conversationId: ConversationId,
    val launchRequest: PhotoViewerLaunchRequest,
) : ConversationScopedNavKey
