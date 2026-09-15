package com.waxd.messaging.data.conversation.store

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversation.model.ParticipantId
import com.waxd.messaging.datamodel.BugleDatabaseOperations
import com.waxd.messaging.datamodel.DataModel
import com.waxd.messaging.datamodel.data.ConversationListItemData
import com.waxd.messaging.datamodel.data.MessageData
import javax.inject.Inject

internal interface ConversationDraftStore {
    fun getSelfParticipantId(conversationId: ConversationId): ParticipantId?

    fun readDraftMessage(
        conversationId: ConversationId,
        selfParticipantId: ParticipantId,
    ): MessageData?

    fun updateDraftMessage(
        conversationId: ConversationId,
        message: MessageData,
    )
}

internal class ConversationDraftStoreImpl @Inject constructor() : ConversationDraftStore {

    override fun getSelfParticipantId(conversationId: ConversationId): ParticipantId? {
        val conversation = ConversationListItemData.getExistingConversation(
            DataModel.get().database,
            conversationId.value,
        ) ?: return null

        return ParticipantId.fromOrNull(conversation.selfId)
    }

    override fun readDraftMessage(
        conversationId: ConversationId,
        selfParticipantId: ParticipantId,
    ): MessageData? {
        return BugleDatabaseOperations.readDraftMessageData(
            DataModel.get().database,
            conversationId.value,
            selfParticipantId.value,
        )
    }

    override fun updateDraftMessage(
        conversationId: ConversationId,
        message: MessageData,
    ) {
        BugleDatabaseOperations.updateDraftMessageData(
            DataModel.get().database,
            conversationId.value,
            message,
            BugleDatabaseOperations.UPDATE_MODE_ADD_DRAFT,
        )
    }
}
