package com.waxd.messaging.di.conversation

import com.waxd.messaging.data.contact.formatter.ContactDestinationFormatter
import com.waxd.messaging.data.contact.formatter.ContactDestinationFormatterImpl
import com.waxd.messaging.data.contact.repository.ContactsRepository
import com.waxd.messaging.data.contact.repository.ContactsRepositoryImpl
import com.waxd.messaging.data.conversation.mapper.ConversationDraftMessageDataMapper
import com.waxd.messaging.data.conversation.mapper.ConversationDraftMessageDataMapperImpl
import com.waxd.messaging.data.conversation.mapper.ConversationMessageDataDraftMapper
import com.waxd.messaging.data.conversation.mapper.ConversationMessageDataDraftMapperImpl
import com.waxd.messaging.data.conversation.mapper.ConversationMessageDetailsMapper
import com.waxd.messaging.data.conversation.mapper.ConversationMessageDetailsMapperImpl
import com.waxd.messaging.data.conversation.mapper.ConversationVCardMetadataMapper
import com.waxd.messaging.data.conversation.mapper.ConversationVCardMetadataMapperImpl
import com.waxd.messaging.data.conversation.platform.MessageDetailsPlatformSource
import com.waxd.messaging.data.conversation.platform.MessageDetailsPlatformSourceImpl
import com.waxd.messaging.data.conversation.repository.ConversationDraftsRepository
import com.waxd.messaging.data.conversation.repository.ConversationDraftsRepositoryImpl
import com.waxd.messaging.data.conversation.repository.ConversationParticipantsRepository
import com.waxd.messaging.data.conversation.repository.ConversationParticipantsRepositoryImpl
import com.waxd.messaging.data.conversation.repository.ConversationVCardMetadataRepository
import com.waxd.messaging.data.conversation.repository.ConversationVCardMetadataRepositoryImpl
import com.waxd.messaging.data.conversation.repository.ConversationsRepository
import com.waxd.messaging.data.conversation.repository.ConversationsRepositoryImpl
import com.waxd.messaging.data.conversation.store.ConversationArchiveStore
import com.waxd.messaging.data.conversation.store.ConversationArchiveStoreImpl
import com.waxd.messaging.data.conversation.store.ConversationDraftStore
import com.waxd.messaging.data.conversation.store.ConversationDraftStoreImpl
import com.waxd.messaging.data.conversation.store.ConversationPinStore
import com.waxd.messaging.data.conversation.store.ConversationPinStoreImpl
import com.waxd.messaging.data.conversation.store.ConversationReadStore
import com.waxd.messaging.data.conversation.store.ConversationReadStoreImpl
import com.waxd.messaging.data.conversation.store.ConversationSelfIdStore
import com.waxd.messaging.data.conversation.store.ConversationSelfIdStoreImpl
import com.waxd.messaging.data.media.repository.ConversationAttachmentsRepository
import com.waxd.messaging.data.media.repository.ConversationAttachmentsRepositoryImpl
import com.waxd.messaging.data.media.repository.ConversationMediaRepository
import com.waxd.messaging.data.media.repository.ConversationMediaRepositoryImpl
import com.waxd.messaging.data.subscription.repository.ConversationSimSelectionRepository
import com.waxd.messaging.data.subscription.repository.ConversationSimSelectionRepositoryImpl
import com.waxd.messaging.data.subscription.repository.SubscriptionsRepository
import com.waxd.messaging.data.subscription.repository.SubscriptionsRepositoryImpl
import com.waxd.messaging.domain.contacts.usecase.IsReadContactsPermissionGranted
import com.waxd.messaging.domain.contacts.usecase.IsReadContactsPermissionGrantedImpl
import com.waxd.messaging.domain.conversation.usecase.action.CheckConversationActionRequirements
import com.waxd.messaging.domain.conversation.usecase.action.CheckConversationActionRequirementsImpl
import com.waxd.messaging.domain.conversation.usecase.action.CreateDefaultSmsRoleRequest
import com.waxd.messaging.domain.conversation.usecase.action.CreateDefaultSmsRoleRequestImpl
import com.waxd.messaging.domain.conversation.usecase.avatar.ResolveAvatarUri
import com.waxd.messaging.domain.conversation.usecase.avatar.ResolveAvatarUriImpl
import com.waxd.messaging.domain.conversation.usecase.draft.GetConversationDraftSendProtocol
import com.waxd.messaging.domain.conversation.usecase.draft.GetConversationDraftSendProtocolImpl
import com.waxd.messaging.domain.conversation.usecase.draft.SendConversationDraft
import com.waxd.messaging.domain.conversation.usecase.draft.SendConversationDraftImpl
import com.waxd.messaging.domain.conversation.usecase.forward.CreateForwardedMessage
import com.waxd.messaging.domain.conversation.usecase.forward.CreateForwardedMessageImpl
import com.waxd.messaging.domain.conversation.usecase.forward.ForwardedMessageSubjectFormatter
import com.waxd.messaging.domain.conversation.usecase.forward.ForwardedMessageSubjectFormatterImpl
import com.waxd.messaging.domain.conversation.usecase.participant.CanAddContact
import com.waxd.messaging.domain.conversation.usecase.participant.CanAddContactImpl
import com.waxd.messaging.domain.conversation.usecase.participant.CanAddMoreConversationParticipants
import com.waxd.messaging.domain.conversation.usecase.participant.CanAddMoreConversationParticipantsImpl
import com.waxd.messaging.domain.conversation.usecase.participant.CanShowOrAddContact
import com.waxd.messaging.domain.conversation.usecase.participant.CanShowOrAddContactImpl
import com.waxd.messaging.domain.conversation.usecase.participant.IsContactSaved
import com.waxd.messaging.domain.conversation.usecase.participant.IsContactSavedImpl
import com.waxd.messaging.domain.conversation.usecase.participant.IsConversationRecipientLimitExceeded
import com.waxd.messaging.domain.conversation.usecase.participant.IsConversationRecipientLimitExceededImpl
import com.waxd.messaging.domain.conversation.usecase.participant.ResolveContactAction
import com.waxd.messaging.domain.conversation.usecase.participant.ResolveContactActionImpl
import com.waxd.messaging.domain.conversation.usecase.participant.ResolveConversationId
import com.waxd.messaging.domain.conversation.usecase.participant.ResolveConversationIdImpl
import com.waxd.messaging.domain.conversation.usecase.telephony.CanPlacePhoneCall
import com.waxd.messaging.domain.conversation.usecase.telephony.CanPlacePhoneCallImpl
import com.waxd.messaging.domain.conversation.usecase.telephony.IsDeviceVoiceCapable
import com.waxd.messaging.domain.conversation.usecase.telephony.IsDeviceVoiceCapableImpl
import com.waxd.messaging.domain.conversation.usecase.telephony.IsEmergencyPhoneNumber
import com.waxd.messaging.domain.conversation.usecase.telephony.IsEmergencyPhoneNumberImpl
import com.waxd.messaging.domain.media.usecase.ResolveAudioDurationMillis
import com.waxd.messaging.domain.media.usecase.ResolveAudioDurationMillisImpl
import com.waxd.messaging.ui.contact.mapper.ContactUiModelMapper
import com.waxd.messaging.ui.contact.mapper.ContactUiModelMapperImpl
import com.waxd.messaging.ui.conversation.attachment.mapper.ConversationVCardAttachmentUiModelMapper
import com.waxd.messaging.ui.conversation.attachment.mapper.ConversationVCardAttachmentUiModelMapperImpl
import com.waxd.messaging.ui.conversation.composer.mapper.ConversationComposerAttachmentUiModelMapper
import com.waxd.messaging.ui.conversation.composer.mapper.ConversationComposerAttachmentUiModelMapperImpl
import com.waxd.messaging.ui.conversation.composer.mapper.ConversationComposerUiStateMapper
import com.waxd.messaging.ui.conversation.composer.mapper.ConversationComposerUiStateMapperImpl
import com.waxd.messaging.ui.conversation.mediapicker.mapper.ConversationDraftAttachmentMapper
import com.waxd.messaging.ui.conversation.mediapicker.mapper.ConversationDraftAttachmentMapperImpl
import com.waxd.messaging.ui.conversation.messagedetails.mapper.MessageDetailsUiStateMapper
import com.waxd.messaging.ui.conversation.messagedetails.mapper.MessageDetailsUiStateMapperImpl
import com.waxd.messaging.ui.conversation.messages.mapper.ConversationMessageUiModelMapper
import com.waxd.messaging.ui.conversation.messages.mapper.ConversationMessageUiModelMapperImpl
import com.waxd.messaging.ui.conversation.metadata.mapper.ConversationMetadataUiStateMapper
import com.waxd.messaging.ui.conversation.metadata.mapper.ConversationMetadataUiStateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ConversationBindsModule {

    @Binds
    @Reusable
    abstract fun bindConversationDraftMessageDataMapper(
        impl: ConversationDraftMessageDataMapperImpl,
    ): ConversationDraftMessageDataMapper

    @Binds
    @Reusable
    abstract fun bindConversationMessageDataDraftMapper(
        impl: ConversationMessageDataDraftMapperImpl,
    ): ConversationMessageDataDraftMapper

    @Binds
    @Reusable
    abstract fun bindConversationMessageDetailsMapper(
        impl: ConversationMessageDetailsMapperImpl,
    ): ConversationMessageDetailsMapper

    @Binds
    @Reusable
    abstract fun bindMessageDetailsPlatformSource(
        impl: MessageDetailsPlatformSourceImpl,
    ): MessageDetailsPlatformSource

    @Binds
    @Reusable
    abstract fun bindConversationDraftStore(
        impl: ConversationDraftStoreImpl,
    ): ConversationDraftStore

    @Binds
    @Reusable
    abstract fun bindConversationReadStore(
        impl: ConversationReadStoreImpl,
    ): ConversationReadStore

    @Binds
    @Reusable
    abstract fun bindConversationPinStore(
        impl: ConversationPinStoreImpl,
    ): ConversationPinStore

    @Binds
    @Reusable
    abstract fun bindConversationArchiveStore(
        impl: ConversationArchiveStoreImpl,
    ): ConversationArchiveStore

    @Binds
    @Reusable
    abstract fun bindConversationDraftsRepository(
        impl: ConversationDraftsRepositoryImpl,
    ): ConversationDraftsRepository

    @Binds
    @Reusable
    abstract fun bindConversationParticipantsRepository(
        impl: ConversationParticipantsRepositoryImpl,
    ): ConversationParticipantsRepository

    @Binds
    @Reusable
    abstract fun bindConversationContactsRepository(
        impl: ContactsRepositoryImpl,
    ): ContactsRepository

    @Binds
    @Reusable
    abstract fun bindCanAddMoreConversationParticipants(
        impl: CanAddMoreConversationParticipantsImpl,
    ): CanAddMoreConversationParticipants

    @Binds
    @Reusable
    abstract fun bindCanAddContact(
        impl: CanAddContactImpl,
    ): CanAddContact

    @Binds
    @Reusable
    abstract fun bindCanShowOrAddContact(
        impl: CanShowOrAddContactImpl,
    ): CanShowOrAddContact

    @Binds
    @Reusable
    abstract fun bindResolveContactAction(
        impl: ResolveContactActionImpl,
    ): ResolveContactAction

    @Binds
    @Reusable
    abstract fun bindContactDestinationFormatter(
        impl: ContactDestinationFormatterImpl,
    ): ContactDestinationFormatter

    @Binds
    @Reusable
    abstract fun bindContactUiModelMapper(
        impl: ContactUiModelMapperImpl,
    ): ContactUiModelMapper

    @Binds
    @Reusable
    abstract fun bindMessageDetailsUiStateMapper(
        impl: MessageDetailsUiStateMapperImpl,
    ): MessageDetailsUiStateMapper

    @Binds
    @Reusable
    abstract fun bindCheckConversationActionRequirements(
        impl: CheckConversationActionRequirementsImpl,
    ): CheckConversationActionRequirements

    @Binds
    @Reusable
    abstract fun bindCreateDefaultSmsRoleRequest(
        impl: CreateDefaultSmsRoleRequestImpl,
    ): CreateDefaultSmsRoleRequest

    @Binds
    @Reusable
    abstract fun bindIsDeviceVoiceCapable(
        impl: IsDeviceVoiceCapableImpl,
    ): IsDeviceVoiceCapable

    @Binds
    @Reusable
    abstract fun bindIsEmergencyPhoneNumber(
        impl: IsEmergencyPhoneNumberImpl,
    ): IsEmergencyPhoneNumber

    @Binds
    @Reusable
    abstract fun bindCanPlacePhoneCall(
        impl: CanPlacePhoneCallImpl,
    ): CanPlacePhoneCall

    @Binds
    @Reusable
    abstract fun bindIsContactSaved(
        impl: IsContactSavedImpl,
    ): IsContactSaved

    @Binds
    @Singleton
    abstract fun bindResolveAudioDurationMillis(
        impl: ResolveAudioDurationMillisImpl,
    ): ResolveAudioDurationMillis

    @Binds
    @Reusable
    abstract fun bindResolveAvatarUri(
        impl: ResolveAvatarUriImpl,
    ): ResolveAvatarUri

    @Binds
    @Reusable
    abstract fun bindCreateForwardedMessage(
        impl: CreateForwardedMessageImpl,
    ): CreateForwardedMessage

    @Binds
    @Reusable
    abstract fun bindGetConversationDraftSendProtocol(
        impl: GetConversationDraftSendProtocolImpl,
    ): GetConversationDraftSendProtocol

    @Binds
    @Reusable
    abstract fun bindIsReadContactsPermissionGranted(
        impl: IsReadContactsPermissionGrantedImpl,
    ): IsReadContactsPermissionGranted

    @Binds
    @Reusable
    abstract fun bindForwardedMessageSubjectFormatter(
        impl: ForwardedMessageSubjectFormatterImpl,
    ): ForwardedMessageSubjectFormatter

    @Binds
    @Reusable
    abstract fun bindResolveConversationId(
        impl: ResolveConversationIdImpl,
    ): ResolveConversationId

    @Binds
    @Reusable
    abstract fun bindIsConversationRecipientLimitExceeded(
        impl: IsConversationRecipientLimitExceededImpl,
    ): IsConversationRecipientLimitExceeded

    @Binds
    @Reusable
    abstract fun bindConversationsRepository(
        impl: ConversationsRepositoryImpl,
    ): ConversationsRepository

    @Binds
    @Reusable
    abstract fun bindConversationSelfIdStore(
        impl: ConversationSelfIdStoreImpl,
    ): ConversationSelfIdStore

    @Binds
    @Reusable
    abstract fun bindSubscriptionsRepository(
        impl: SubscriptionsRepositoryImpl,
    ): SubscriptionsRepository

    @Binds
    @Reusable
    abstract fun bindConversationAttachmentRepository(
        impl: ConversationAttachmentsRepositoryImpl,
    ): ConversationAttachmentsRepository

    @Binds
    @Singleton
    abstract fun bindConversationSimSelectionRepository(
        impl: ConversationSimSelectionRepositoryImpl,
    ): ConversationSimSelectionRepository

    @Binds
    @Reusable
    abstract fun bindConversationDraftAttachmentMapper(
        impl: ConversationDraftAttachmentMapperImpl,
    ): ConversationDraftAttachmentMapper

    @Binds
    @Reusable
    abstract fun bindConversationComposerAttachmentUiModelMapper(
        impl: ConversationComposerAttachmentUiModelMapperImpl,
    ): ConversationComposerAttachmentUiModelMapper

    @Binds
    abstract fun bindConversationComposerUiStateMapper(
        impl: ConversationComposerUiStateMapperImpl,
    ): ConversationComposerUiStateMapper

    @Binds
    abstract fun bindConversationMessageUiModelMapper(
        impl: ConversationMessageUiModelMapperImpl,
    ): ConversationMessageUiModelMapper

    @Binds
    @Reusable
    abstract fun bindConversationVCardAttachmentUiModelMapper(
        impl: ConversationVCardAttachmentUiModelMapperImpl,
    ): ConversationVCardAttachmentUiModelMapper

    @Binds
    @Reusable
    abstract fun bindConversationVCardMetadataRepository(
        impl: ConversationVCardMetadataRepositoryImpl,
    ): ConversationVCardMetadataRepository

    @Binds
    @Reusable
    abstract fun bindConversationVCardMetadataMapper(
        impl: ConversationVCardMetadataMapperImpl,
    ): ConversationVCardMetadataMapper

    @Binds
    @Reusable
    abstract fun bindConversationMediaRepository(
        impl: ConversationMediaRepositoryImpl,
    ): ConversationMediaRepository

    @Binds
    abstract fun bindConversationMetadataUiStateMapper(
        impl: ConversationMetadataUiStateMapperImpl,
    ): ConversationMetadataUiStateMapper

    @Binds
    @Reusable
    abstract fun bindSendConversationDraft(
        impl: SendConversationDraftImpl,
    ): SendConversationDraft
}
