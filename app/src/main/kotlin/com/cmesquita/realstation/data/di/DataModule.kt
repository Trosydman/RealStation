package com.cmesquita.realstation.data.di

import android.content.Context
import com.cmesquita.realstation.data.RealStateRepository
import com.cmesquita.realstation.data.RealStateRepositoryImpl
import com.cmesquita.realstation.data.remote.AvivAPI
import com.cmesquita.realstation.data.remote.NetworkConnectionInterceptor
import com.cmesquita.realstation.data.remote.model.RealStateDTOMapper
import com.cmesquita.realstation.data.remote.model.RealStateListDTOMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindRealStateRepository(realStateRepositoryImpl: RealStateRepositoryImpl): RealStateRepository

    companion object {
        @Singleton
        @Provides
        fun provideRealStateDTOMapper() = RealStateDTOMapper()

        @Singleton
        @Provides
        fun provideRealStateListDTOMapper() = RealStateListDTOMapper()

        @Singleton
        @Provides
        fun provideAvivAPI(@ApplicationContext context: Context): AvivAPI {
            val httpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()

            return Retrofit.Builder()
                .baseUrl(AvivAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(AvivAPI::class.java)
        }
    }
}
