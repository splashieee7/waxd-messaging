package com.waxd.messaging.ui.conversation.common

import com.waxd.messaging.data.conversation.model.ConversationId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

internal interface ConversationScreenDelegate<T> {
    val state: StateFlow<T>

    fun bind(
        scope: CoroutineScope,
        conversationIdFlow: StateFlow<ConversationId?>,
    )
}
