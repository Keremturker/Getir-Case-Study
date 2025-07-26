package com.kturker.feature.product.domain.usecase

import androidx.paging.PagingData
import com.kturker.core.domain.ProductItem
import com.kturker.feature.product.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductPagingUseCase @Inject constructor(
    private val repository: ProductRepository
) {
      operator fun invoke(): Flow<PagingData<ProductItem>> =
        repository.getProductsPaging()
}