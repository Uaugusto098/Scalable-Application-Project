package com.example.skeletonapp.Model.osc

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OscModule{


    @Binds
    abstract fun bindOscModule(impl: UdpOscSender): OscSender
}

