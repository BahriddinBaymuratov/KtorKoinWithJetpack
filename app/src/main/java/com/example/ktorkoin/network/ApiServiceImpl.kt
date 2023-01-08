package com.example.ktorkoin.network

import android.util.Log
import com.example.ktorkoin.model.StoreDTO
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient

class ApiServiceImpl(
    private val httpClient: HttpClient
) : ApiService{
    private val BASE_URL = "https://fakestoreapi.com"

    override suspend fun getProductById(id: Int): Flow<Response<StoreDTO>> = flow {
        try {
            val response = httpClient.get<StoreDTO>{
                url("$BASE_URL/products/$id")
            }
            emit(Response.Success(response))
        }catch (e:Exception){
            emit(Response.Error(e.message.toString()))
        }
    }

    override suspend fun getProducts(): Flow<Response<List<StoreDTO>>> = flow {
        try {
            val response = httpClient.get<List<StoreDTO>> {
                url("$BASE_URL/products")
            }
            emit(Response.Success(response))
        }catch (e:Exception){
            emit(Response.Error(e.message.toString()))
            Log.d("ApiService", "getProducts: ${e.message}")
        }
    }
}