package com.example.ktorkoin.ui.screen.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorkoin.network.ApiService
import com.example.ktorkoin.network.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductLIstViewModel(
    private val apiService: ApiService
): ViewModel() {
    private val _state: MutableStateFlow<ProductListState> = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state

    init {
        getAllProductList()
    }

    private fun getAllProductList() {
        viewModelScope.launch {
            apiService.getProducts().collect{response->
                _state.update {
                    ProductListState(isLoading = true)
                }
                delay(500L)
                when(response){
                    is Response.Error -> {
                        _state.update {
                            ProductListState(isLoading = false, error = response.text)
                        }
                    }
                    is Response.Success -> {
                        _state.update {
                            ProductListState(isLoading = false, postList = response.data)
                        }
                    }
                }
            }
        }
    }
}