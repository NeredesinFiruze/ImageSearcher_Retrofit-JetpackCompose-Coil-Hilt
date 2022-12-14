package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote.model

data class Model(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)