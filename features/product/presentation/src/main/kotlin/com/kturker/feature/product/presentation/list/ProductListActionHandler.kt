package com.kturker.feature.product.presentation.list

interface ProductListActionHandler {
    fun onAction(action: ProductListAction)
}

sealed interface ProductListAction {
    data class Add(val id: String, val source: Source) : ProductListAction
    data class Remove(val id: String, val source: Source) : ProductListAction

    enum class Source {
        Suggested, All
    }
}