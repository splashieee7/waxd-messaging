package com.waxd.messaging.di.core

import com.waxd.messaging.debug.DebugSimEmulationSource
import com.waxd.messaging.debug.DebugSimEmulationStore
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DebugProvidesModule {

    @Provides
    @Reusable
    fun provideDebugSimEmulationSource(): DebugSimEmulationSource = DebugSimEmulationStore
}
