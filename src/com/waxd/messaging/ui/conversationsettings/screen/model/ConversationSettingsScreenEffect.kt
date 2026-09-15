package com.waxd.messaging.ui.conversationsettings.screen.model

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.ui.contact.model.AddContactRequest

internal sealed interface ConversationSettingsScreenEffect {

    data class OpenNotificationChannelSettings(
        val conversationId: ConversationId,
        val conversationTitle: String,
    ) : ConversationSettingsScreenEffect

    data class CopyToClipboard(
        val text: String,
    ) : ConversationSettingsScreenEffect

    data class ShowMessage(
        val messageResId: Int,
    ) : ConversationSettingsScreenEffect

    data class PlacePhoneCall(
        val destination: String,
    ) : ConversationSettingsScreenEffect

    data class ShowContactCard(
        val contactId: Long,
        val contactLookupKey: String,
    ) : ConversationSettingsScreenEffect

    data class AddContact(
        val request: AddContactRequest,
    ) : ConversationSettingsScreenEffect
}
