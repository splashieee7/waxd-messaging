package com.waxd.messaging.di.photoviewer

import com.waxd.messaging.data.media.repository.PhotoViewerRepository
import com.waxd.messaging.data.media.repository.PhotoViewerRepositoryImpl
import com.waxd.messaging.domain.photoviewer.usecase.NormalizePhotoViewerUri
import com.waxd.messaging.domain.photoviewer.usecase.NormalizePhotoViewerUriImpl
import com.waxd.messaging.domain.photoviewer.usecase.PreparePhotoViewerSendUri
import com.waxd.messaging.domain.photoviewer.usecase.PreparePhotoViewerSendUriImpl
import com.waxd.messaging.domain.photoviewer.usecase.ResolveConversationPhotoViewerInitialOccurrenceIndex
import com.waxd.messaging.domain.photoviewer.usecase.ResolveConversationPhotoViewerInitialOccurrenceIndexImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PhotoViewerBindsModule {

    @Binds
    @Reusable
    abstract fun bindNormalizePhotoViewerUri(
        impl: NormalizePhotoViewerUriImpl,
    ): NormalizePhotoViewerUri

    @Binds
    @Reusable
    abstract fun bindResolveConversationPhotoViewerInitialOccurrenceIndex(
        impl: ResolveConversationPhotoViewerInitialOccurrenceIndexImpl,
    ): ResolveConversationPhotoViewerInitialOccurrenceIndex

    @Binds
    @Reusable
    abstract fun bindPhotoViewerRepository(
        impl: PhotoViewerRepositoryImpl,
    ): PhotoViewerRepository

    @Binds
    @Reusable
    abstract fun bindPreparePhotoViewerSendUri(
        impl: PreparePhotoViewerSendUriImpl,
    ): PreparePhotoViewerSendUri
}
