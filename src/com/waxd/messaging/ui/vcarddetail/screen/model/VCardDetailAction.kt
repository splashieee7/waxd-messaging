package com.waxd.messaging.ui.vcarddetail.screen.model

import com.waxd.messaging.data.vcarddetail.model.VCardFieldAction

internal sealed interface VCardDetailAction {

    data class FieldClicked(
        val action: VCardFieldAction,
    ) : VCardDetailAction

    data class FieldLongClicked(
        val value: String,
    ) : VCardDetailAction

    data object AddToContactsClicked : VCardDetailAction
}
