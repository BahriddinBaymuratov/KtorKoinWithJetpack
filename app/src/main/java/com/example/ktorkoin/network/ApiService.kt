package com.example.ktorkoin.network

import com.example.ktorkoin.model.StoreDTO
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun getProductById(id:Int): Flow<Response<StoreDTO>>
    suspend fun getProducts(): Flow<Response<List<StoreDTO>>>
}