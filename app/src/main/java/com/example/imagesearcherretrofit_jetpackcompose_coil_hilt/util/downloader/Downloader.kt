package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.downloader

interface Downloader {

    fun downloadFile(url: String): Long
}