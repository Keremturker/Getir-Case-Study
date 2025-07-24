package com.kturker.feature.product.data

import com.kturker.feature.product.data.model.AllProductsResponseDto
import retrofit2.Response
import retrofit2.http.GET

internal interface ProductService {

    @GET(ProductEndpoints.GetAllProduct)
    suspend fun getAllProducts(): Response<List<AllProductsResponseDto>>

}