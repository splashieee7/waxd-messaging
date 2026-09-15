package com.waxd.messaging.di.vcarddetail

import com.waxd.messaging.data.vcarddetail.mapper.VCardDetailMapper
import com.waxd.messaging.data.vcarddetail.mapper.VCardDetailMapperImpl
import com.waxd.messaging.data.vcarddetail.repository.VCardDetailRepository
import com.waxd.messaging.data.vcarddetail.repository.VCardDetailRepositoryImpl
import com.waxd.messaging.domain.vcarddetail.usecase.AddVCardToContacts
import com.waxd.messaging.domain.vcarddetail.usecase.AddVCardToContactsImpl
import com.waxd.messaging.ui.vcarddetail.screen.mapper.VCardDetailUiStateMapper
import com.waxd.messaging.ui.vcarddetail.screen.mapper.VCardDetailUiStateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class VCardDetailBindsModule {

    @Binds
    @Reusable
    abstract fun bindVCardDetailRepository(
        impl: VCardDetailRepositoryImpl,
    ): VCardDetailRepository

    @Binds
    @Reusable
    abstract fun bindVCardDetailMapper(
        impl: VCardDetailMapperImpl,
    ): VCardDetailMapper

    @Binds
    @Reusable
    abstract fun bindAddVCardToContacts(
        impl: AddVCardToContactsImpl,
    ): AddVCardToContacts

    @Binds
    @Reusable
    abstract fun bindVCardDetailUiStateMapper(
        impl: VCardDetailUiStateMapperImpl,
    ): VCardDetailUiStateMapper
}
