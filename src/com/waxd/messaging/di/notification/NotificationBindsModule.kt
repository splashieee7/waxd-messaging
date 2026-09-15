package com.waxd.messaging.di.notification

import com.waxd.messaging.domain.notification.usecase.GenerateNotificationId
import com.waxd.messaging.domain.notification.usecase.GenerateNotificationIdImpl
import com.waxd.messaging.domain.notification.usecase.MigrateConversationNotificationChannels
import com.waxd.messaging.domain.notification.usecase.MigrateConversationNotificationChannelsImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationBindsModule {

    @Binds
    @Singleton
    abstract fun bindGenerateNotificationId(
        impl: GenerateNotificationIdImpl,
    ): GenerateNotificationId

    @Binds
    @Reusable
    abstract fun bindMigrateConversationNotificationChannels(
        impl: MigrateConversationNotificationChannelsImpl,
    ): MigrateConversationNotificationChannels
}
