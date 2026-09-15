package com.waxd.messaging.data.conversation.store

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.datamodel.BugleDatabaseOperations
import com.waxd.messaging.datamodel.DataModel
import com.waxd.messaging.datamodel.MessagingContentProvider
import javax.inject.Inject

internal interface ConversationPinStore {
    fun pinConversation(conversationId: ConversationId)
    fun unpinConversation(conversationId: ConversationId)
}

internal class ConversationPinStoreImpl @Inject constructor() : ConversationPinStore {

    override fun pinConversation(conversationId: ConversationId) {
        setPinned(
            conversationId = conversationId,
            isPinned = true,
        )
    }

    override fun unpinConversation(conversationId: ConversationId) {
        setPinned(
            conversationId = conversationId,
            isPinned = false,
        )
    }

    private fun setPinned(
        conversationId: ConversationId,
        isPinned: Boolean,
    ) {
        val database = DataModel.get().database

        database.beginTransaction()
        try {
            BugleDatabaseOperations.updateConversationPinStatusInTransaction(
                database,
                conversationId.value,
                isPinned,
            )
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }

        MessagingContentProvider.notifyConversationListChanged()
        MessagingContentProvider.notifyConversationMetadataChanged(conversationId.value)
    }
}
