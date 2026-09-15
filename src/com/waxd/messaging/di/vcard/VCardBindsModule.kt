package com.waxd.messaging.di.vcard

import com.waxd.messaging.data.vcard.mapper.VCardEntrySummarizer
import com.waxd.messaging.data.vcard.mapper.VCardEntrySummarizerImpl
import com.waxd.messaging.data.vcard.parser.VCardParser
import com.waxd.messaging.data.vcard.parser.VCardParserImpl
import com.waxd.messaging.data.vcard.photo.VCardPhotoDownscaler
import com.waxd.messaging.data.vcard.photo.VCardPhotoDownscalerImpl
import com.waxd.messaging.data.vcard.repository.VCardEntryRepository
import com.waxd.messaging.data.vcard.repository.VCardEntryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class VCardBindsModule {

    @Binds
    @Reusable
    abstract fun bindVCardParser(
        impl: VCardParserImpl,
    ): VCardParser

    @Binds
    @Reusable
    abstract fun bindVCardPhotoDownscaler(
        impl: VCardPhotoDownscalerImpl,
    ): VCardPhotoDownscaler

    @Binds
    @Reusable
    abstract fun bindVCardEntrySummarizer(
        impl: VCardEntrySummarizerImpl,
    ): VCardEntrySummarizer

    @Binds
    @Singleton
    abstract fun bindVCardEntryRepository(
        impl: VCardEntryRepositoryImpl,
    ): VCardEntryRepository
}
