package com.cmesquita.realstation.utils.coroutines

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineModule {

    @Binds
    fun bindAppCoroutineDispatchers(appCoroutineDispatchersImpl: AppCoroutineDispatchersImpl): AppCoroutineDispatchers
}
