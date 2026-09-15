package com.waxd.messaging.di.conversationlist

import com.waxd.messaging.ui.conversationlist.delegate.ConversationListActionsDelegate
import com.waxd.messaging.ui.conversationlist.delegate.ConversationListActionsDelegateImpl
import com.waxd.messaging.ui.conversationlist.delegate.ConversationListOptimisticSnapshotDelegate
import com.waxd.messaging.ui.conversationlist.delegate.ConversationListOptimisticSnapshotDelegateImpl
import com.waxd.messaging.ui.conversationlist.delegate.ConversationListSelectionDelegate
import com.waxd.messaging.ui.conversationlist.delegate.ConversationListSelectionDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ConversationListViewModelBindsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindConversationListSelectionDelegate(
        impl: ConversationListSelectionDelegateImpl,
    ): ConversationListSelectionDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationListActionsDelegate(
        impl: ConversationListActionsDelegateImpl,
    ): ConversationListActionsDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationListOptimisticSnapshotDelegate(
        impl: ConversationListOptimisticSnapshotDelegateImpl,
    ): ConversationListOptimisticSnapshotDelegate
}
