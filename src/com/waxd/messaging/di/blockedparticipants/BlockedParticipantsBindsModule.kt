package com.waxd.messaging.di.blockedparticipants

import com.waxd.messaging.data.blockedparticipants.repository.BlockedParticipantsRepository
import com.waxd.messaging.data.blockedparticipants.repository.BlockedParticipantsRepositoryImpl
import com.waxd.messaging.domain.blockedparticipants.usecase.DeleteDirectChats
import com.waxd.messaging.domain.blockedparticipants.usecase.DeleteDirectChatsImpl
import com.waxd.messaging.ui.blockedparticipants.screen.mapper.BlockedParticipantsUiStateMapper
import com.waxd.messaging.ui.blockedparticipants.screen.mapper.BlockedParticipantsUiStateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BlockedParticipantsBindsModule {

    @Binds
    @Reusable
    abstract fun bindBlockedParticipantsRepository(
        impl: BlockedParticipantsRepositoryImpl,
    ): BlockedParticipantsRepository

    @Binds
    @Reusable
    abstract fun bindBlockedParticipantsUiStateMapper(
        impl: BlockedParticipantsUiStateMapperImpl,
    ): BlockedParticipantsUiStateMapper

    @Binds
    @Reusable
    abstract fun bindDeleteDirectChats(
        impl: DeleteDirectChatsImpl,
    ): DeleteDirectChats
}
