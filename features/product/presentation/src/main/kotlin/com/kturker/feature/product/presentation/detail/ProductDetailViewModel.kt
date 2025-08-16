package com.kturker.feature.product.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.domain.usecase.AddToCartUseCase
import com.kturker.core.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.core.domain.usecase.RemoveFromCartUseCase
import com.kturker.core.presentation.CoreViewModel
import com.kturker.core.presentation.KEY_ARGS
import com.kturker.core.presentation.getJson
import com.kturker.core.presentation.putJson
import com.kturker.feature.product.contract.ProductDetailArgs
import com.kturker.feature.product.domain.usecase.GetQuantityByIdUseCase
import com.kturker.feature.product.presentation.navigation.ProductNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigation: ProductNavigation,
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase,
    private val getQuantityById: GetQuantityByIdUseCase,
    getCartTotalPrice: GetCartTotalPriceUseCase
) : CoreViewModel(), ProductDetailAction {

    val args: ProductDetailArgs by lazy {
        savedStateHandle.getJson<ProductDetailArgs>(KEY_ARGS)
    }

    private val _uiState = MutableStateFlow(value = ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    val cartTotalPrice = getCartTotalPrice()

    var quantityById = flowOf<Int>()

    fun setArgs(newArgs: ProductDetailArgs) {
        if (savedStateHandle.contains(KEY_ARGS).not()) {
            savedStateHandle.putJson(KEY_ARGS, newArgs)
        }

        initUiState()
    }

    fun initUiState() {
        quantityById = getQuantityById(args.id)

        _uiState.update {
            it.copy(
                imageUrl = args.imageUrl,
                description = args.description,
                priceText = args.priceText,
                name = args.name
            )
        }
    }

    override fun navigateUp() {
        navigation.navigateUp()
    }

    override fun addToCart() {
        val item = ProductItem(
            id = args.id,
            name = args.name,
            description = args.description,
            imageUrl = args.imageUrl,
            price = args.price,
            priceText = args.priceText
        )

        viewModelScope.launch {
            addToCart.invoke(item)
        }
    }

    override fun removeFromCart() {
        viewModelScope.launch {
            removeFromCart.invoke(id = args.id)
        }
    }

    override fun navigateToCartScreen() {
        if (args.isCameFromCart) {
            navigation.navigateUp()
        } else {
            navigation.navigateToCartScreen()
        }
    }

    fun updateCartTotalPrice(totalPrice: String) {
        _uiState.update { state ->
            state.copy(totalPriceFormatted = totalPrice)
        }
    }

    fun updateProductQuantity(quantity: Int) {
        _uiState.update { state ->
            state.copy(productQuantity = quantity)
        }
    }
}
