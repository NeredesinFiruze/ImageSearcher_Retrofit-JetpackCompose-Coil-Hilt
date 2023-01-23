package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.presentation

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.downloader.AndroidDownloader

@Composable
fun MainScreen(context: Context, viewModel: MainViewModel = hiltViewModel()) {



    Column(
        Modifier.fillMaxSize()
    ) {
        TextField(
            value = viewModel.searchString.value,
            onValueChange = { viewModel.getList(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Init(context)
    }
}

@Composable
fun Init(context: Context, viewModel: MainViewModel= hiltViewModel()) {

    val state = viewModel.state.value
    val error by remember { viewModel.loadError }

    val downloader = AndroidDownloader(context)

    if (error.isNotEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = error)
                Button(
                    onClick = {
                        viewModel.getList(viewModel.searchString.value)
                        viewModel.loadError.value = ""
                    })
                {
                    Text(text = "Try Again")
                }
            }
        }
    }
    
    LazyColumn{
        items(state){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Column() {
                        AsyncImage(
                            model = it.url,
                            contentDescription = null,
                            modifier = Modifier
                                .size(250.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = it.name, textAlign = TextAlign.Center)
                    }
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        onClick = {
                            downloader.downloadFile(it.url)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                Divider(modifier = Modifier.padding(16.dp))
            }
        }
    }
}