package com.cmesquita.realstation.ui.di

import com.cmesquita.realstation.ui.details.model.RealStateDetailsMapper
import com.cmesquita.realstation.ui.list.model.RealStateListItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UIModule {

    @Singleton
    @Provides
    fun provideRealStateListItemMapper(): RealStateListItemMapper = RealStateListItemMapper()

    @Singleton
    @Provides
    fun provideRealStateDetailsMapper(): RealStateDetailsMapper = RealStateDetailsMapper()
}
