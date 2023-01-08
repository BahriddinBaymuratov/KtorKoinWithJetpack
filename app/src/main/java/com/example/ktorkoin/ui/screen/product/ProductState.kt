package com.example.ktorkoin.ui.screen.product

import com.example.ktorkoin.model.StoreDTO

data class ProductState(
    val isLoading: Boolean = false,
    val error: String = "",
    val product: StoreDTO? = null,
)