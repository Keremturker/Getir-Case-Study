package com.kturker.feature.product.domain.usecase

import androidx.paging.PagingData
import com.kturker.core.domain.model.ProductItem
import com.kturker.feature.product.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SuggestedProductPagingUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<PagingData<ProductItem>> = repository.getSuggestedProductsPaging()
}
