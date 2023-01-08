package com.example.ktorkoin.ui.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorkoin.network.ApiService
import com.example.ktorkoin.network.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val apiService: ApiService,
) : ViewModel() {
    private val _state: MutableStateFlow<ProductState> = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> get() = _state

    fun getProductById(id: Int) {
        viewModelScope.launch {
            apiService.getProductById(id).collect { response ->
                _state.update {
                    ProductState(isLoading = true)
                }
                delay(1000L)
                when (response) {
                    is Response.Error -> {
                        _state.update {
                            ProductState(isLoading = false, error = response.text)
                        }
                    }
                    is Response.Success -> {
                        _state.update {
                            ProductState(isLoading = false, product = response.data)
                        }
                    }
                }
            }
        }
    }
}