package com.kturker.feature.product.presentation.list

import com.kturker.core.domain.ProductItem

internal sealed interface RefreshResult {
    data class PartialSuccess(
        val products: List<ProductItem>?,
        val suggestedProducts: List<ProductItem>?,
        val errorMessage: String? = null
    ) : RefreshResult

    data class Error(val message: String) : RefreshResult
}