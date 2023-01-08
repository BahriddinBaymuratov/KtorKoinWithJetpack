package com.example.ktorkoin.model

@kotlinx.serialization.Serializable
data class StoreDTO(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val price: Double,
)
