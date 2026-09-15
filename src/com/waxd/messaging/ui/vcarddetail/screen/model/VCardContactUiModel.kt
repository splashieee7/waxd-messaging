package com.waxd.messaging.ui.vcarddetail.screen.model

import androidx.compose.runtime.Immutable
import com.waxd.messaging.data.vcard.model.VCardAvatarPhoto
import com.waxd.messaging.data.vcarddetail.model.VCardFieldAction
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal data class VCardContactUiModel(
    val displayName: String?,
    val normalizedDestination: String?,
    val avatarPhoto: VCardAvatarPhoto?,
    val fields: ImmutableList<VCardFieldUiModel>,
)

@Immutable
internal data class VCardFieldUiModel(
    val value: String,
    val label: String?,
    val action: VCardFieldAction,
)
