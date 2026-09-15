package com.waxd.messaging.ui

import com.waxd.messaging.testutil.androidManifestDocument
import com.waxd.messaging.testutil.elementsByTagName
import com.waxd.messaging.ui.classzero.ClassZeroActivity
import com.waxd.messaging.ui.conversation.ConversationActivity
import com.waxd.messaging.ui.conversation.LaunchConversationActivity
import com.waxd.messaging.ui.license.LicenseActivity
import org.junit.Assert.assertTrue
import org.junit.Test

internal class ActivityPermissionGateTest {

    private val applicationId = "com.waxd.messaging"

    private val gatingBases = setOf(
        BugleComponentActivity::class.java,
    )

    private val intentionallyUngated = setOf<Class<*>>(
        MainActivity::class.java,
        ConversationActivity::class.java,
        LaunchConversationActivity::class.java,
        LicenseActivity::class.java,
        ClassZeroActivity::class.java,
    )

    @Test
    fun everyManifestActivityIsPermissionGated() {
        val activityNames = manifestActivityNames()

        assertTrue(
            "No <activity> entries parsed from the manifest; the gate test is not exercising anything",
            activityNames.isNotEmpty(),
        )

        val offenders = activityNames
            .map { name -> Class.forName(name) }
            .filterNot { activityClass -> activityClass in intentionallyUngated }
            .filterNot { activityClass -> isGated(activityClass) }
            .map { it.name }

        assertTrue(
            "Activities not extending a permission-gated base. Extend BugleComponentActivity, " +
                "or add to the allowlist in this test with a reason: $offenders",
            offenders.isEmpty(),
        )
    }

    private fun isGated(activityClass: Class<*>): Boolean {
        return gatingBases.any { base -> base.isAssignableFrom(activityClass) }
    }

    private fun manifestActivityNames(): List<String> {
        return androidManifestDocument()
            .elementsByTagName(tagName = "activity")
            .map { element -> element.getAttribute("android:name") }
            .filter { name -> name.isNotEmpty() }
            .map { name -> resolveClassName(name) }
    }

    private fun resolveClassName(name: String): String {
        return when {
            name.startsWith(".") -> applicationId + name
            !name.contains(".") -> "$applicationId.$name"
            else -> name
        }
    }
}
