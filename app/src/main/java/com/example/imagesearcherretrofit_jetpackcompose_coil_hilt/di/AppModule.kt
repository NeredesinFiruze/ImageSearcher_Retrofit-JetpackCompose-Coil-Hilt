package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.di

import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote.ApiRepository
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote.PictureApi
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): PictureApi{
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PictureApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: PictureApi) = ApiRepository(api)
}