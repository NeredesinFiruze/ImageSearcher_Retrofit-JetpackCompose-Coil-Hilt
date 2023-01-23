package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.presentation.MainScreen
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.ui.theme.ImageSearcherRetrofitJetpackComposeCoilHiltTheme
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.downloader.AndroidDownloader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearcherRetrofitJetpackComposeCoilHiltTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(this)
                }
            }
        }
    }
}