package com.waxd.messaging.di.debugmmsconfig

import com.waxd.messaging.data.debugmmsconfig.repository.MmsConfigRepository
import com.waxd.messaging.data.debugmmsconfig.repository.MmsConfigRepositoryImpl
import com.waxd.messaging.ui.debug.screen.mapper.DebugMmsConfigUiStateMapper
import com.waxd.messaging.ui.debug.screen.mapper.DebugMmsConfigUiStateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DebugMmsConfigBindsModule {

    @Binds
    @Reusable
    abstract fun bindMmsConfigRepository(
        impl: MmsConfigRepositoryImpl,
    ): MmsConfigRepository

    @Binds
    @Reusable
    abstract fun bindDebugMmsConfigUiStateMapper(
        impl: DebugMmsConfigUiStateMapperImpl,
    ): DebugMmsConfigUiStateMapper
}
