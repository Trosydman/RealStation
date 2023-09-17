package com.cmesquita.realstation.utils

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Singleton
    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources =
        context.resources
}
