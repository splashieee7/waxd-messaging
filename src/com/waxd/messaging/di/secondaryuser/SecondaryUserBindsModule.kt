package com.waxd.messaging.di.secondaryuser

import com.waxd.messaging.data.secondaryuser.SecondaryUserMessageResolver
import com.waxd.messaging.data.secondaryuser.SecondaryUserMessageResolverImpl
import com.waxd.messaging.data.secondaryuser.SecondaryUserNotifier
import com.waxd.messaging.data.secondaryuser.SecondaryUserNotifierImpl
import com.waxd.messaging.data.secondaryuser.SmsContactNameLookup
import com.waxd.messaging.data.secondaryuser.SmsContactNameLookupImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SecondaryUserBindsModule {

    @Binds
    @Reusable
    abstract fun bindSecondaryUserMessageResolver(
        impl: SecondaryUserMessageResolverImpl,
    ): SecondaryUserMessageResolver

    @Binds
    @Reusable
    abstract fun bindSecondaryUserNotifier(
        impl: SecondaryUserNotifierImpl,
    ): SecondaryUserNotifier

    @Binds
    @Reusable
    abstract fun bindSmsContactNameLookup(
        impl: SmsContactNameLookupImpl,
    ): SmsContactNameLookup
}
