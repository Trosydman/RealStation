package com.cmesquita.realstation.ui.di

import com.cmesquita.realstation.ui.details.model.RealStateDetailsMapper
import com.cmesquita.realstation.ui.list.model.RealStateListItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UIModule {

    @Provides
    fun provideRealStateListItemMapper(): RealStateListItemMapper = RealStateListItemMapper()

    @Provides
    fun provideRealStateDetailsMapper(): RealStateDetailsMapper = RealStateDetailsMapper()
}
