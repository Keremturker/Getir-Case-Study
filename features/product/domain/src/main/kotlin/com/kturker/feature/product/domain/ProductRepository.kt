package com.kturker.feature.product.domain

import com.kturker.contract.ServiceResult
import com.kturker.feature.product.domain.model.AllProductModel
import com.kturker.feature.product.domain.model.AllSuggestedProductModel

interface ProductRepository {

  suspend  fun getAllProducts(): ServiceResult<List<AllProductModel>>

  suspend fun getAllSuggestedProducts(): ServiceResult<List<AllSuggestedProductModel>>
}