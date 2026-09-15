package com.waxd.messaging.ui.conversationlist.archived.mapper

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversationlist.model.ConversationListSnapshot
import com.waxd.messaging.ui.conversationlist.archived.model.ArchivedConversationListUiState as State
import com.waxd.messaging.ui.conversationlist.mapper.ConversationListContentUiStateMapper
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList

internal interface ArchivedConversationListUiStateMapper {
    fun map(
        snapshot: ConversationListSnapshot,
        selectedConversationIds: ImmutableList<ConversationId>,
        isDebugEnabled: Boolean,
    ): State
}

internal class ArchivedConversationListUiStateMapperImpl @Inject constructor(
    private val contentMapper: ConversationListContentUiStateMapper,
) : ArchivedConversationListUiStateMapper {

    override fun map(
        snapshot: ConversationListSnapshot,
        selectedConversationIds: ImmutableList<ConversationId>,
        isDebugEnabled: Boolean,
    ): State {
        val content = contentMapper.map(
            snapshot = snapshot,
            selectedConversationIds = selectedConversationIds,
            openedConversationId = null,
        )

        return State(
            content = content,
            selectedCount = selectedConversationIds.size,
            isDebugEnabled = isDebugEnabled,
        )
    }
}
