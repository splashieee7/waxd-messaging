package com.waxd.messaging.di.conversation

import com.waxd.messaging.domain.conversation.usecase.draft.ResolveConversationDraftSendProtocol
import com.waxd.messaging.domain.conversation.usecase.draft.ResolveConversationDraftSendProtocolImpl
import com.waxd.messaging.domain.conversation.usecase.draft.ResolveDraftAttachmentsWithinLimit
import com.waxd.messaging.domain.conversation.usecase.draft.ResolveDraftAttachmentsWithinLimitImpl
import com.waxd.messaging.ui.conversation.audio.delegate.ConversationAudioRecordingDelegate
import com.waxd.messaging.ui.conversation.audio.delegate.ConversationAudioRecordingDelegateImpl
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationComposerAttachmentsDelegate
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationComposerAttachmentsDelegateImpl
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationDraftDelegate
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationDraftDelegateImpl
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationDraftEditorDelegate
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationDraftEditorDelegateImpl
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationSubscriptionSelectionDelegate
import com.waxd.messaging.ui.conversation.composer.delegate.ConversationSubscriptionSelectionDelegateImpl
import com.waxd.messaging.ui.conversation.focus.delegate.ConversationFocusDelegate
import com.waxd.messaging.ui.conversation.focus.delegate.ConversationFocusDelegateImpl
import com.waxd.messaging.ui.conversation.mediapicker.delegate.ConversationMediaPickerDelegate
import com.waxd.messaging.ui.conversation.mediapicker.delegate.ConversationMediaPickerDelegateImpl
import com.waxd.messaging.ui.conversation.messages.delegate.ConversationMessageSelectionDelegate
import com.waxd.messaging.ui.conversation.messages.delegate.ConversationMessageSelectionDelegateImpl
import com.waxd.messaging.ui.conversation.messages.delegate.ConversationMessagesDelegate
import com.waxd.messaging.ui.conversation.messages.delegate.ConversationMessagesDelegateImpl
import com.waxd.messaging.ui.conversation.metadata.delegate.ConversationMetadataDelegate
import com.waxd.messaging.ui.conversation.metadata.delegate.ConversationMetadataDelegateImpl
import com.waxd.messaging.ui.conversation.recipientpicker.delegate.ConversationResolutionDelegate
import com.waxd.messaging.ui.conversation.recipientpicker.delegate.ConversationResolutionDelegateImpl
import com.waxd.messaging.ui.conversation.recipientpicker.delegate.SelectedRecipientsDelegate
import com.waxd.messaging.ui.conversation.recipientpicker.delegate.SelectedRecipientsDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ConversationViewModelBindsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindConversationAudioRecordingDelegate(
        impl: ConversationAudioRecordingDelegateImpl,
    ): ConversationAudioRecordingDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationComposerAttachmentsDelegate(
        impl: ConversationComposerAttachmentsDelegateImpl,
    ): ConversationComposerAttachmentsDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindResolveConversationDraftSendProtocol(
        impl: ResolveConversationDraftSendProtocolImpl,
    ): ResolveConversationDraftSendProtocol

    @Binds
    @ViewModelScoped
    abstract fun bindResolveDraftAttachmentsWithinLimit(
        impl: ResolveDraftAttachmentsWithinLimitImpl,
    ): ResolveDraftAttachmentsWithinLimit

    @Binds
    @ViewModelScoped
    abstract fun bindConversationDraftDelegate(
        impl: ConversationDraftDelegateImpl,
    ): ConversationDraftDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationDraftEditorDelegate(
        impl: ConversationDraftEditorDelegateImpl,
    ): ConversationDraftEditorDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationMediaPickerDelegate(
        impl: ConversationMediaPickerDelegateImpl,
    ): ConversationMediaPickerDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationMessageSelectionDelegate(
        impl: ConversationMessageSelectionDelegateImpl,
    ): ConversationMessageSelectionDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationMessagesDelegate(
        impl: ConversationMessagesDelegateImpl,
    ): ConversationMessagesDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationMetadataDelegate(
        impl: ConversationMetadataDelegateImpl,
    ): ConversationMetadataDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationFocusDelegate(
        impl: ConversationFocusDelegateImpl,
    ): ConversationFocusDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationSubscriptionSelectionDelegate(
        impl: ConversationSubscriptionSelectionDelegateImpl,
    ): ConversationSubscriptionSelectionDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindSelectedRecipientsDelegate(
        impl: SelectedRecipientsDelegateImpl,
    ): SelectedRecipientsDelegate

    @Binds
    @ViewModelScoped
    abstract fun bindConversationResolutionDelegate(
        impl: ConversationResolutionDelegateImpl,
    ): ConversationResolutionDelegate
}
