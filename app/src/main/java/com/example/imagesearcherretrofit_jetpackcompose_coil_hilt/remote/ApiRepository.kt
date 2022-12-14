package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote

import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote.model.Model
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.Resource
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val api: PictureApi
){
    suspend fun getModel(query: String): Resource<Model>{

        val response = try {
            api.getApi(query)
        }catch (e: Exception){
            return Resource.Error("error")
        }
        return Resource.Success(response)
    }
}