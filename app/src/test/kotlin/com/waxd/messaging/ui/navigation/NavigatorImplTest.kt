package com.waxd.messaging.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.testutil.TEST_CONVERSATION_ID as CONVERSATION_ID
import com.waxd.messaging.ui.conversation.navigation.ConversationNavKey
import com.waxd.messaging.ui.conversation.navigation.NewChatNavKey
import com.waxd.messaging.ui.conversationlist.navigation.ArchivedConversationListNavKey
import com.waxd.messaging.ui.conversationsettings.navigation.ConversationSettingsNavKey
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NavigatorImplTest {

    private var didFinish = false

    @Test
    fun closeConversation_finishesWhenNothingRemains() {
        val backStack = mutableListOf<NavKey>(
            ConversationNavKey(conversationId = CONVERSATION_ID),
            ConversationSettingsNavKey(conversationId = CONVERSATION_ID),
        )

        navigator(backStack = backStack).closeConversation(conversationId = CONVERSATION_ID)

        assertTrue(didFinish)
    }

    @Test
    fun closeConversation_dropsEveryTrailingDestinationOfThatConversation() {
        val backStack = mutableListOf(
            NewChatNavKey,
            ConversationNavKey(conversationId = CONVERSATION_ID),
            ConversationSettingsNavKey(conversationId = CONVERSATION_ID),
        )

        navigator(backStack = backStack).closeConversation(conversationId = CONVERSATION_ID)

        assertFalse(didFinish)
        assertEquals(listOf(NewChatNavKey), backStack)
    }

    @Test
    fun closeConversation_keepsDestinationsOfOtherConversations() {
        val backStack = mutableListOf<NavKey>(
            ConversationNavKey(conversationId = ConversationId("other")),
            ConversationSettingsNavKey(conversationId = CONVERSATION_ID),
        )

        navigator(backStack = backStack).closeConversation(conversationId = CONVERSATION_ID)

        assertEquals(
            listOf(ConversationNavKey(conversationId = ConversationId("other"))),
            backStack,
        )
    }

    @Test
    fun removeDestination_dropsItFromTheMiddleOfTheBackStack() {
        val backStack = mutableListOf(
            NewChatNavKey,
            ArchivedConversationListNavKey,
            ConversationNavKey(conversationId = CONVERSATION_ID),
        )

        navigator(backStack = backStack)
            .removeDestination(destination = ArchivedConversationListNavKey)

        assertFalse(didFinish)
        assertEquals(
            listOf(NewChatNavKey, ConversationNavKey(conversationId = CONVERSATION_ID)),
            backStack,
        )
    }

    @Test
    fun removeDestination_finishesWhenNothingRemains() {
        val backStack = mutableListOf<NavKey>(ArchivedConversationListNavKey)

        navigator(backStack = backStack)
            .removeDestination(destination = ArchivedConversationListNavKey)

        assertTrue(didFinish)
    }

    @Test
    fun back_finishesWhenBackStackHasSingleEntry() {
        val backStack = mutableListOf<NavKey>(NewChatNavKey)

        navigator(backStack = backStack).back()

        assertTrue(didFinish)
        assertEquals(listOf(NewChatNavKey), backStack)
    }

    @Test
    fun back_removesLastEntryWhenBackStackHasMultipleEntries() {
        val backStack = mutableListOf(
            NewChatNavKey,
            ConversationNavKey(conversationId = CONVERSATION_ID),
        )

        navigator(backStack = backStack).back()

        assertFalse(didFinish)
        assertEquals(listOf(NewChatNavKey), backStack)
    }

    private fun navigator(backStack: MutableList<NavKey>): Navigator {
        return NavigatorImpl(
            backStack = backStack,
            navigationReducer = NavigationReducerImpl(),
            onFinish = { didFinish = true },
        )
    }
}
