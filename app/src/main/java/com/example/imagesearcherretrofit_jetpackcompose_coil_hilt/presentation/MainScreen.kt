package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
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
        Init()
    }
}

@Composable
fun Init(viewModel: MainViewModel= hiltViewModel()) {

    val state = viewModel.state.value
    val error by remember { viewModel.loadError }

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
                AsyncImage(
                    model = it.url,
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                )
                Text(text = it.name, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}