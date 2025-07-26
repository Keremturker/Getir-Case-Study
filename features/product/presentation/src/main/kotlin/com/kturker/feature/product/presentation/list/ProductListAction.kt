package com.kturker.feature.product.presentation.list

import com.kturker.core.domain.ProductItem

internal interface ProductListAction {
    fun addToCart(item: ProductItem)
    fun removeFromCart(item: ProductItem)
    fun onFetchData(defaultOnLoading: Boolean = true)
    fun navigateToDetailScreen(item: ProductItem)
    fun navigateToCartScreen()
}
