package com.waxd.messaging.ui.contact.navigation

import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.ui.contact.model.AddContactRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class AddContactNavKey(
    val request: AddContactRequest,
) : NavKey
