package com.waxd.messaging.ui.onboarding.screen.model

internal sealed interface OnboardingAction {

    data object NextClicked : OnboardingAction

    data object SettingsClicked : OnboardingAction
}
