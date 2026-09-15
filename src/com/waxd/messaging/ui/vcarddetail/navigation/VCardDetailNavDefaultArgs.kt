package com.waxd.messaging.ui.vcarddetail.navigation

import android.os.Bundle
import androidx.core.net.toUri
import com.waxd.messaging.ui.vcarddetail.screen.VCARD_DETAIL_URI_ARG

internal fun vCardDetailDefaultArgs(navKey: VCardDetailNavKey): Bundle {
    return Bundle().apply {
        putParcelable(VCARD_DETAIL_URI_ARG, navKey.uri.toUri())
    }
}
