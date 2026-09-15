package com.waxd.messaging.domain.blockedparticipants.usecase

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.datamodel.action.DeleteConversationAction
import javax.inject.Inject

internal interface DeleteDirectChats {
    operator fun invoke(conversationIds: List<ConversationId>)
}

internal class DeleteDirectChatsImpl @Inject constructor() : DeleteDirectChats {

    override operator fun invoke(conversationIds: List<ConversationId>) {
        conversationIds.forEach { conversationId ->
            DeleteConversationAction.deleteConversation(conversationId.value, Long.MAX_VALUE)
        }
    }
}
