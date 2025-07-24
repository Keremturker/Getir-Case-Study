package com.kturker.feature.product.domain

import com.kturker.contract.RestResult
import com.kturker.feature.product.domain.model.AllProductModel

interface ProductRepository {

  suspend  fun getAllProducts(): RestResult<List<AllProductModel>>
}