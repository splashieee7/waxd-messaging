package com.waxd.messaging.ui.conversation.recipientpicker.delegate.conversationresolutiondelegate

import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.domain.conversation.usecase.participant.ResolveConversationId
import com.waxd.messaging.domain.conversation.usecase.participant.model.ResolveConversationIdResult
import com.waxd.messaging.testutil.MainDispatcherRule
import com.waxd.messaging.ui.conversation.recipientpicker.delegate.ConversationResolutionDelegateImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
internal abstract class BaseConversationResolutionDelegateTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    protected val resolveConversationId = mockk<ResolveConversationId>()

    @Before
    fun setUpDefaultStubs() {
        coEvery {
            resolveConversationId(destinations = any())
        } returns ResolveConversationIdResult.Resolved(conversationId = DEFAULT_CONVERSATION_ID)
    }

    protected fun createDelegate(): ConversationResolutionDelegateImpl {
        return ConversationResolutionDelegateImpl(
            resolveConversationId = resolveConversationId,
            mainDispatcher = mainDispatcherRule.testDispatcher,
        )
    }

    protected fun TestScope.createBoundDelegate(): ConversationResolutionDelegateImpl {
        return createDelegate().also { delegate ->
            delegate.bind(scope = backgroundScope)
            runCurrent()
        }
    }

    protected fun givenResolutionSuspendsUntil(
        gate: CompletableDeferred<ResolveConversationIdResult>,
    ) {
        coEvery {
            resolveConversationId(destinations = any())
        } coAnswers { gate.await() }
    }

    protected companion object {
        val DEFAULT_CONVERSATION_ID = ConversationId("conversation-default")
    }
}
