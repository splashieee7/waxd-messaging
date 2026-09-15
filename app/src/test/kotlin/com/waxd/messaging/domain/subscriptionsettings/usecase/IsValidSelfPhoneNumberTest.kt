package com.waxd.messaging.domain.subscriptionsettings.usecase

import android.content.Context
import android.telephony.SubscriptionManager
import com.waxd.messaging.Factory
import com.waxd.messaging.FactoryTestAccess
import com.waxd.messaging.data.subscription.model.SubId
import com.waxd.messaging.testutil.FakeBuglePrefs
import com.waxd.messaging.testutil.installTestFactory
import com.waxd.messaging.util.PhoneUtils
import io.mockk.every
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowSubscriptionManager

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class IsValidSelfPhoneNumberTest {

    private val useCase = IsValidSelfPhoneNumberImpl()

    private lateinit var context: Context

    @Before
    fun setUp() {
        ShadowSubscriptionManager.reset()
        context = RuntimeEnvironment.getApplication().applicationContext
        installTestFactory(context = context, prefs = FakeBuglePrefs())
        givenSimOnEstonianCarrier()
        every { Factory.get().getPhoneUtils(any()) } returns PhoneUtils(SUB_ID.value)
    }

    @After
    fun tearDown() {
        ShadowSubscriptionManager.reset()
        FactoryTestAccess.reset()
    }

    @Test
    fun invokeAcceptsAnEmptyNumber() {
        assertTrue(
            "emptying the field is how the number is handed back to the SIM, so it can never" +
                " be rejected as invalid",
            useCase(SUB_ID, ""),
        )
    }

    @Test
    fun invokeAcceptsAPhoneNumber() {
        assertTrue(useCase(SUB_ID, "5555 0001"))
    }

    @Test
    fun invokeRejectsTextThatIsNotAPhoneNumber() {
        assertFalse(useCase(SUB_ID, "DROP TABLE messages"))
    }

    private fun givenSimOnEstonianCarrier() {
        val subscriptionManager = context.getSystemService(SubscriptionManager::class.java)
        shadowOf(subscriptionManager).setActiveSubscriptionInfos(
            ShadowSubscriptionManager.SubscriptionInfoBuilder.newBuilder()
                .setId(SUB_ID.value)
                .setCountryIso("ee")
                .setNumber("+37253953334")
                .buildSubscriptionInfo(),
        )
    }

    private companion object {
        private val SUB_ID = SubId(2)
    }
}
