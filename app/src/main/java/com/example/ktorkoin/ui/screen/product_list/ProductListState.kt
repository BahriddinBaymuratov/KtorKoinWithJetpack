package com.example.ktorkoin.ui.screen.product_list

import com.example.ktorkoin.model.StoreDTO

data class ProductListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val postList: List<StoreDTO> = emptyList(),
)