package com.waxd.messaging.di.conversationsettings

import com.waxd.messaging.data.conversationsettings.repository.ConversationNotificationRepository
import com.waxd.messaging.data.conversationsettings.repository.ConversationNotificationRepositoryImpl
import com.waxd.messaging.data.conversationsettings.repository.ConversationSettingsRepository
import com.waxd.messaging.data.conversationsettings.repository.ConversationSettingsRepositoryImpl
import com.waxd.messaging.domain.conversationsettings.usecase.SetConversationSelfParticipantId
import com.waxd.messaging.domain.conversationsettings.usecase.SetConversationSelfParticipantIdImpl
import com.waxd.messaging.ui.conversationsettings.screen.mapper.ConversationSettingsUiStateMapper
import com.waxd.messaging.ui.conversationsettings.screen.mapper.ConversationSettingsUiStateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ConversationSettingsBindsModule {

    @Binds
    @Reusable
    abstract fun bindConversationSettingsUiStateMapper(
        impl: ConversationSettingsUiStateMapperImpl,
    ): ConversationSettingsUiStateMapper

    @Binds
    @Reusable
    abstract fun bindConversationSettingsRepository(
        impl: ConversationSettingsRepositoryImpl,
    ): ConversationSettingsRepository

    @Binds
    @Reusable
    abstract fun bindConversationNotificationRepository(
        impl: ConversationNotificationRepositoryImpl,
    ): ConversationNotificationRepository

    @Binds
    @Reusable
    abstract fun bindSetConversationSelfParticipantId(
        impl: SetConversationSelfParticipantIdImpl,
    ): SetConversationSelfParticipantId
}
