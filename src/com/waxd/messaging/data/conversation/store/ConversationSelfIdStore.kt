package com.waxd.messaging.data.conversation.store

import android.content.ContentValues
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.datamodel.BugleDatabaseOperations
import com.waxd.messaging.datamodel.DataModel
import com.waxd.messaging.datamodel.DatabaseHelper.ConversationColumns
import javax.inject.Inject

internal interface ConversationSelfIdStore {
    fun updateSelfId(conversationId: ConversationId, selfId: ParticipantId)
}

internal class ConversationSelfIdStoreImpl @Inject constructor() : ConversationSelfIdStore {

    override fun updateSelfId(
        conversationId: ConversationId,
        selfId: ParticipantId,
    ) {
        val values = ContentValues().apply {
            put(ConversationColumns.CURRENT_SELF_ID, selfId.value)
        }

        BugleDatabaseOperations.updateConversationRowIfExists(
            DataModel.get().database,
            conversationId.value,
            values,
        )
    }
}
