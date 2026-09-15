package com.waxd.messaging.di.conversationpicker

import com.waxd.messaging.ui.conversationpicker.delegate.DraftDelegate
import com.waxd.messaging.ui.conversationpicker.delegate.DraftDelegateImpl
import com.waxd.messaging.ui.conversationpicker.delegate.TargetsDelegate
import com.waxd.messaging.ui.conversationpicker.delegate.TargetsDelegateImpl
import com.waxd.messaging.ui.subscription.delegate.SimSelectionDelegate
import com.waxd.messaging.ui.subscription.delegate.SimSelectionDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ConversationPickerViewModelBindsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTargetsDelegate(
        impl: TargetsDelegateImpl,
    ): TargetsDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindDraftDelegate(
        impl: DraftDelegateImpl,
    ): DraftDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindSimSelectionDelegate(
        impl: SimSelectionDelegateImpl,
    ): SimSelectionDelegate
}
