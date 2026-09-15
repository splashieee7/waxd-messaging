package com.waxd.messaging.di.shareintent

import com.waxd.messaging.data.shareintent.repository.SharedAttachmentRepository
import com.waxd.messaging.data.shareintent.repository.SharedAttachmentRepositoryImpl
import com.waxd.messaging.domain.shareintent.usecase.BuildSharedConversationDraft
import com.waxd.messaging.domain.shareintent.usecase.BuildSharedConversationDraftImpl
import com.waxd.messaging.domain.shareintent.usecase.ResolveSharedContentType
import com.waxd.messaging.domain.shareintent.usecase.ResolveSharedContentTypeImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ShareIntentBindsModule {

    @Binds
    @Reusable
    abstract fun bindSharedAttachmentRepository(
        impl: SharedAttachmentRepositoryImpl,
    ): SharedAttachmentRepository

    @Binds
    @Reusable
    abstract fun bindResolveSharedContentType(
        impl: ResolveSharedContentTypeImpl,
    ): ResolveSharedContentType

    @Binds
    @Reusable
    abstract fun bindBuildSharedConversationDraft(
        impl: BuildSharedConversationDraftImpl,
    ): BuildSharedConversationDraft
}
