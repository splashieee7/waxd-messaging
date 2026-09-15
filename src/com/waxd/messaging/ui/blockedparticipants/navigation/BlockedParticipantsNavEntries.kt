package com.waxd.messaging.ui.blockedparticipants.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.R
import com.waxd.messaging.ui.blockedparticipants.screen.BlockedParticipantsScreen
import com.waxd.messaging.ui.blockedparticipants.screen.BlockedParticipantsViewModel
import com.waxd.messaging.ui.blockedparticipants.screen.rememberBlockedParticipantsEffectHandler
import com.waxd.messaging.ui.contact.navigation.navigateToAddContact
import com.waxd.messaging.ui.conversation.navigation.rememberConversationNavigator
import com.waxd.messaging.ui.navigation.LocalNavigator
import com.waxd.messaging.ui.navigation.paneTitleMetadata

internal fun EntryProviderScope<NavKey>.blockedParticipantsEntries() {
    entry<BlockedParticipantsNavKey>(
        metadata = paneTitleMetadata(R.string.blocked_contacts_title),
        content = blockedParticipantsRouteContent(),
    )
}

private fun blockedParticipantsRouteContent(): @Composable (BlockedParticipantsNavKey) -> Unit {
    return {
        val conversationNavigator = rememberConversationNavigator()
        val navigator = LocalNavigator.current
        val effectHandler = rememberBlockedParticipantsEffectHandler(
            onNavigateToAddContact = { request ->
                navigator.navigateToAddContact(request = request)
            },
        )

        BlockedParticipantsScreen(
            screenModel = hiltViewModel<BlockedParticipantsViewModel>(),
            effectHandler = effectHandler,
            onNavigateBack = navigator::back,
            onNavigateToConversation = { conversationId ->
                conversationNavigator.replaceWithConversation(conversationId = conversationId)
            },
        )
    }
}
