package com.waxd.messaging.ui.conversation.screen.simselector

import androidx.compose.ui.test.onAllNodesWithTag
import com.waxd.messaging.testutil.TEST_WAIT_TIMEOUT_MILLIS
import com.waxd.messaging.ui.conversation.CONVERSATION_SIM_SELECTOR_SHEET_TEST_TAG
import com.waxd.messaging.ui.conversation.composer.model.ConversationSimSelectorUiState
import com.waxd.messaging.ui.conversation.screen.BaseConversationScreenTest
import com.waxd.messaging.ui.conversation.testAttSubscription
import com.waxd.messaging.ui.conversation.testVerizonSubscription
import kotlinx.collections.immutable.persistentListOf

internal abstract class BaseConversationScreenSimSelectorIntegrationTest :
    BaseConversationScreenTest() {

    protected fun setSimSelectorContent(
        screenModel: ScreenModelHandle,
        simSelector: ConversationSimSelectorUiState,
    ) {
        val uiState = createPresentUiState(
            messages = createMessages(
                count = 2,
                latestMessageId = "message-2",
                latestMessageIncoming = false,
            ),
        )
        screenModel.scaffoldUiStateFlow.value = uiState.copy(
            composer = uiState.composer.copy(
                simSelector = simSelector,
            ),
        )
        setContent(screenModel = screenModel.model)
    }

    protected fun waitForSheetNodeCount(expectedCount: Int) {
        composeTestRule.waitUntil(timeoutMillis = TEST_WAIT_TIMEOUT_MILLIS) {
            composeTestRule
                .onAllNodesWithTag(CONVERSATION_SIM_SELECTOR_SHEET_TEST_TAG)
                .fetchSemanticsNodes()
                .size == expectedCount
        }
    }

    protected fun createSingleSimSelector(): ConversationSimSelectorUiState {
        return ConversationSimSelectorUiState(
            subscriptions = persistentListOf(testVerizonSubscription),
            selectedSubscription = testVerizonSubscription,
        )
    }

    protected fun createMultiSimSelector(): ConversationSimSelectorUiState {
        return ConversationSimSelectorUiState(
            subscriptions = persistentListOf(testVerizonSubscription, testAttSubscription),
            selectedSubscription = testVerizonSubscription,
        )
    }
}
