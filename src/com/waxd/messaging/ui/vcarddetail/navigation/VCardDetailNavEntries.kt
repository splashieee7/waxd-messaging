package com.waxd.messaging.ui.vcarddetail.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.R
import com.waxd.messaging.ui.navigation.LocalNavigator
import com.waxd.messaging.ui.navigation.SeededViewModelStoreOwner
import com.waxd.messaging.ui.navigation.paneTitleMetadata
import com.waxd.messaging.ui.vcarddetail.screen.VCardDetailScreen
import com.waxd.messaging.ui.vcarddetail.screen.VCardDetailViewModel
import com.waxd.messaging.ui.vcarddetail.screen.rememberVCardDetailEffectHandler

internal fun EntryProviderScope<NavKey>.vCardDetailEntries() {
    entry<VCardDetailNavKey>(
        metadata = paneTitleMetadata(R.string.vcard_detail_activity_title),
        content = vCardDetailRouteContent(),
    )
}

private fun vCardDetailRouteContent(): @Composable (VCardDetailNavKey) -> Unit {
    return { navKey ->
        val navigator = LocalNavigator.current
        val defaultArgs = remember(navKey) {
            vCardDetailDefaultArgs(navKey = navKey)
        }

        SeededViewModelStoreOwner(defaultArgs = defaultArgs) {
            val effectHandler = rememberVCardDetailEffectHandler()

            VCardDetailScreen(
                screenModel = hiltViewModel<VCardDetailViewModel>(),
                effectHandler = effectHandler,
                onNavigateBack = navigator::back,
            )
        }
    }
}
