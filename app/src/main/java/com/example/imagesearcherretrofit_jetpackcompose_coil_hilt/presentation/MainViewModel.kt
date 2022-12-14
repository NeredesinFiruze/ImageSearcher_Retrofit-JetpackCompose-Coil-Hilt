package com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.remote.ApiRepository
import com.example.imagesearcherretrofit_jetpackcompose_coil_hilt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _state = mutableStateOf<List<ModelEntry>>(listOf())
    val state = _state

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var searchString = mutableStateOf("")

    init {
        getList("ataturk")
    }

    fun getList(query: String){
        searchString.value = query
        isLoading.value = true

        viewModelScope.launch {

            when(val result = repository.getModel(query)){
                is Resource.Error -> {
                    loadError.value = result.message!!
                }
                is Resource.Success -> {
                    val entry = result.data!!.hits.mapIndexed { _, hit ->
                        ModelEntry(hit.tags,hit.largeImageURL)
                    }
                    _state.value = entry
                }
            }
        }
    }
}