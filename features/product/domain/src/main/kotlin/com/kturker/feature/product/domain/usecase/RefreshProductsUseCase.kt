package com.kturker.feature.product.domain.usecase

import com.kturker.contract.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val fetchProducts: ProductsUseCase,
    private val fetchSuggestedProducts: SuggestedProductsUseCase
) {
    suspend operator fun invoke(): Flow<String?> {
        return combine(
            flow = fetchProducts(),
            flow2 = fetchSuggestedProducts()
        ) { allProductsResult, suggestedResult ->

            val products = (allProductsResult as? ResultState.Success)?.data
            val suggested = (suggestedResult as? ResultState.Success)?.data

            val allProductsError = (allProductsResult as? ResultState.Error)?.message
            val suggestedError = (suggestedResult as? ResultState.Error)?.message

            return@combine when {
                products != null || suggested != null -> {
                    val combinedErrorMessage =
                        listOfNotNull(allProductsError, suggestedError).joinToString(" & ")
                    combinedErrorMessage.ifBlank { null }
                }

                else -> {
                    allProductsError ?: suggestedError ?: "Unknown error"
                }
            }
        }
    }
}