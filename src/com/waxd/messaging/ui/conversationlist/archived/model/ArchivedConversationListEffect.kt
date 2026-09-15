package com.waxd.messaging.ui.conversationlist.archived.model

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.ui.contact.model.AddContactRequest
import kotlinx.collections.immutable.ImmutableList

internal sealed interface ArchivedConversationListEffect {

    data object OpenDebugOptions : ArchivedConversationListEffect

    data class PlaceCall(
        val destination: String,
    ) : ArchivedConversationListEffect

    data class ShowContactCard(
        val contactId: Long,
        val contactLookupKey: String,
    ) : ArchivedConversationListEffect

    data class AddContact(
        val request: AddContactRequest,
    ) : ArchivedConversationListEffect

    data class ConversationsUnarchived(
        val conversationIds: ImmutableList<ConversationId>,
    ) : ArchivedConversationListEffect
}
