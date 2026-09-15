package com.waxd.messaging.ui.host

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.waxd.messaging.ui.appsettings.navigation.settingsEntries
import com.waxd.messaging.ui.blockedparticipants.navigation.blockedParticipantsEntries
import com.waxd.messaging.ui.contact.navigation.addContactEntries
import com.waxd.messaging.ui.conversation.navigation.conversationEntries
import com.waxd.messaging.ui.conversationlist.navigation.ConversationListNavKey
import com.waxd.messaging.ui.conversationlist.navigation.conversationListEntries
import com.waxd.messaging.ui.conversationpicker.navigation.forwardMessageEntries
import com.waxd.messaging.ui.conversationsettings.navigation.conversationSettingsEntries
import com.waxd.messaging.ui.license.navigation.licenseEntries
import com.waxd.messaging.ui.onboarding.navigation.onboardingEntries
import com.waxd.messaging.ui.photoviewer.navigation.photoViewerEntries
import com.waxd.messaging.ui.vcarddetail.navigation.vCardDetailEntries

internal fun appNavEntryProvider(): (NavKey) -> NavEntry<NavKey> {
    return entryProvider {
        conversationListEntries()
        addContactEntries()
        onboardingEntries(destinationAfterOnboarding = ConversationListNavKey)
        conversationEntries()
        conversationSettingsEntries()
        forwardMessageEntries()
        vCardDetailEntries()
        photoViewerEntries()
        blockedParticipantsEntries()
        settingsEntries()
        licenseEntries()
    }
}
