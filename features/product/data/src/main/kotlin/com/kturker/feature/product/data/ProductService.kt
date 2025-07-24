package com.kturker.feature.product.data

import com.kturker.feature.product.data.model.AllProductsResponseDto
import com.kturker.feature.product.data.model.AllSuggestedProductsResponseDto
import retrofit2.Response
import retrofit2.http.GET

internal interface ProductService {

    @GET(value = ProductEndpoints.GetAllProduct)
    suspend fun getAllProducts(): Response<List<AllProductsResponseDto>>

    @GET(value = ProductEndpoints.GetAllSuggestedProduct)
    suspend fun getAllSuggestedProducts(): Response<List<AllSuggestedProductsResponseDto>>

}