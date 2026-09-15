package com.waxd.messaging.ui.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.waxd.messaging.R
import com.waxd.messaging.ui.navigation.LocalNavigator
import com.waxd.messaging.ui.navigation.paneTitleMetadata
import com.waxd.messaging.ui.onboarding.screen.OnboardingScreen
import com.waxd.messaging.ui.onboarding.screen.OnboardingViewModel
import com.waxd.messaging.ui.onboarding.screen.rememberOnboardingEffectHandler

internal fun EntryProviderScope<NavKey>.onboardingEntries(
    destinationAfterOnboarding: NavKey,
) {
    entry<OnboardingNavKey>(
        metadata = paneTitleMetadata(R.string.app_name),
        content = onboardingRouteContent(
            destinationAfterOnboarding = destinationAfterOnboarding,
        ),
    )
}

private fun onboardingRouteContent(
    destinationAfterOnboarding: NavKey,
): @Composable (OnboardingNavKey) -> Unit {
    return {
        val navigator = LocalNavigator.current
        val effectHandler = rememberOnboardingEffectHandler()

        OnboardingScreen(
            screenModel = hiltViewModel<OnboardingViewModel>(),
            effectHandler = effectHandler,
            onNavigateBack = navigator::back,
            onOnboardingComplete = {
                navigator.replaceTop(destination = destinationAfterOnboarding)
            },
        )
    }
}
