package com.kturker.feature.product.data

import com.kturker.feature.product.data.model.ProductsResponseDto
import com.kturker.feature.product.data.model.SuggestedProductsResponseDto
import retrofit2.Response
import retrofit2.http.GET

internal interface ProductService {

    @GET(value = ProductEndpoints.GetProducts)
    suspend fun getProducts(): Response<List<ProductsResponseDto>>

    @GET(value = ProductEndpoints.GetSuggestedProducts)
    suspend fun getSuggestedProducts(): Response<List<SuggestedProductsResponseDto>>

}