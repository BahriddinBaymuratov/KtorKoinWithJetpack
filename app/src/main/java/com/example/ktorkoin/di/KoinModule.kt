package com.example.ktorkoin.di

import com.example.ktorkoin.network.ApiService
import com.example.ktorkoin.network.ApiServiceImpl
import com.example.ktorkoin.ui.screen.product.ProductViewModel
import com.example.ktorkoin.ui.screen.product_list.ProductLIstViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        isLenient = true
                        encodeDefaults = false
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    factory<ApiService> {
        ApiServiceImpl(get())
    }
    viewModel {
        ProductLIstViewModel(get())
    }
    viewModel {
        ProductViewModel(get())
    }

}