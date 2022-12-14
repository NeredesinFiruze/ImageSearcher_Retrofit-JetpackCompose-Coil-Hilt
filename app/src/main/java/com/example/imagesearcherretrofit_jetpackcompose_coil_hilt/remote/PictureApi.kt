package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote

import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote.model.Model
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.Constants.KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {
    @GET("api/")
    suspend fun getApi(
        @Query("q") query: String,
        @Query("key") key: String = KEY
    ): Model
}