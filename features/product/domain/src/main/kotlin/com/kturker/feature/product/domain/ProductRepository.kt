package com.kturker.feature.product.domain

import com.kturker.contract.ResultState
import com.kturker.feature.product.domain.model.AllProductModel
import com.kturker.feature.product.domain.model.AllSuggestedProductModel

interface ProductRepository {

  suspend  fun getAllProducts(): ResultState<List<AllProductModel>>

  suspend fun getAllSuggestedProducts(): ResultState<List<AllSuggestedProductModel>>
}