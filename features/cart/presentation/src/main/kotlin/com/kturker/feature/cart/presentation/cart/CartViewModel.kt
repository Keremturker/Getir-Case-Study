package com.kturker.feature.cart.presentation.cart

import androidx.lifecycle.viewModelScope
import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.domain.usecase.AddToCartUseCase
import com.kturker.core.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.core.domain.usecase.RemoveFromCartUseCase
import com.kturker.core.presentation.CoreViewModel
import com.kturker.feature.cart.domain.usecase.ClearCartUseCase
import com.kturker.feature.cart.domain.usecase.GetCartProductsUseCase
import com.kturker.feature.cart.domain.usecase.GetSuggestedProductUseCase
import com.kturker.feature.cart.presentation.navigation.CartNavigation
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CartViewModel @Inject constructor(
    private val navigation: CartNavigation,
    private val clearCart: ClearCartUseCase,
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase,
    private val stringResourceManager: StringResourceManager,
    getSuggestedProduct: GetSuggestedProductUseCase,
    getCartTotalPrice: GetCartTotalPriceUseCase,
    getCartProducts: GetCartProductsUseCase
) : CoreViewModel(), CartAction {

    private val _uiState = MutableStateFlow(
        CartUiState(
            title = stringResourceManager[ML::cartTitle],
            completeOrderButtonTitle = stringResourceManager[ML::completeOrderTitle],
            suggestedProductTitle = stringResourceManager[ML::suggestedProductListTitle]
        )
    )
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    private val _dialogEvents = MutableSharedFlow<DialogEvent>()
    val dialogEvents: SharedFlow<DialogEvent> = _dialogEvents.asSharedFlow()

    val cartTotalPrice = getCartTotalPrice()

    val suggestedProduct = getSuggestedProduct()

    val cartProducts = getCartProducts()

    override fun navigateUp() {
        navigation.navigateUp()
    }

    override fun addToCard(id: String) {
        val cartItem = prepareProductItem(id = id) ?: return

        viewModelScope.launch {
            addToCart.invoke(item = cartItem)
        }
    }

    override fun removeFromCard(id: String) {
        viewModelScope.launch {
            removeFromCart.invoke(id = id)
        }
    }

    override fun completeOrderDialog() {
        viewModelScope.launch {
            val totalPrice = _uiState.value.totalPriceFormatted

            _dialogEvents.emit(
                value = DialogEvent.ShowDialog(
                    description = stringResourceManager[ML::clearCartDialogDescription, totalPrice],
                    positiveButtonText = stringResourceManager[ML::close],
                    onPositive = {
                        clearCart()
                    }
                )
            )
        }
    }

    override fun navigateToDetail(id: String) {
        val productItem = prepareProductItem(id = id) ?: return
        navigation.navigateToDetail(productItem = productItem)
    }

    override fun clearCartDialog() {
        viewModelScope.launch {
            _dialogEvents.emit(
                value = DialogEvent.ShowDialog(
                    description = stringResourceManager[ML::completeOrderDialogDescription],
                    positiveButtonText = stringResourceManager[ML::yes],
                    negativeButtonText = stringResourceManager[ML::abort],
                    onPositive = {
                        clearCart()
                    }
                )
            )
        }
    }


    fun updateCartTotalPrice(totalPrice: String) {
        _uiState.update { state ->
            state.copy(totalPriceFormatted = totalPrice)
        }
    }

    fun updateSuggestedProduct(products: List<ProductItem>) {
        _uiState.update { state ->
            state.copy(suggestedProducts = products)
        }
    }


    fun updateCartProducts(cartProducts: List<CartItem>) {

        _uiState.update { state ->
            state.copy(cartProducts = cartProducts)
        }

        if (cartProducts.isEmpty()) {
            navigation.popBackStackToProductList()
        }
    }


    private fun clearCart() {
        clearCart.invoke()
    }

    private fun prepareProductItem(id: String): ProductItem? {
        return _uiState.value.cartProducts.find { it.id == id }.toProductItem()
            ?: _uiState.value.suggestedProducts.find { it.id == id }
    }

    private fun CartItem?.toProductItem(): ProductItem? {
        return this?.let {
            ProductItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = it.imageURL,
                price = it.price,
                priceText = it.priceText
            )
        }
    }
}
