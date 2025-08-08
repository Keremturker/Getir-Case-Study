package com.kturker.feature.cart.presentation.cart

import app.cash.turbine.test
import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.domain.usecase.AddToCartUseCase
import com.kturker.core.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.core.domain.usecase.RemoveFromCartUseCase
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricNotificationManager
import com.kturker.feature.cart.domain.usecase.ClearCartUseCase
import com.kturker.feature.cart.domain.usecase.GetCartProductsUseCase
import com.kturker.feature.cart.domain.usecase.GetSuggestedProductUseCase
import com.kturker.feature.cart.presentation.navigation.CartNavigation
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class CartViewModelTest {

    @MockK
    private lateinit var mockNavigation: CartNavigation

    @MockK
    private lateinit var mockClearCart: ClearCartUseCase

    @MockK
    private lateinit var mockAddToCart: AddToCartUseCase

    @MockK
    private lateinit var mockRemoveFromCart: RemoveFromCartUseCase

    @MockK
    private lateinit var mockGetSuggestedProduct: GetSuggestedProductUseCase

    @MockK
    private lateinit var mockGetCartTotalPrice: GetCartTotalPriceUseCase

    @MockK
    private lateinit var mockGetCartProducts: GetCartProductsUseCase

    @MockK
    private lateinit var progressCentricNotificationManager: ProgressCentricNotificationManager

    private lateinit var viewModel: CartViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every {
            mockGetCartTotalPrice.invoke()
        } returns flowOf()

        every {
            mockGetSuggestedProduct.invoke()
        } returns flowOf()

        every {
            mockGetCartProducts.invoke()
        } returns flowOf()

        viewModel = CartViewModel(
            navigation = mockNavigation,
            clearCart = mockClearCart,
            addToCart = mockAddToCart,
            removeFromCart = mockRemoveFromCart,
            getSuggestedProduct = mockGetSuggestedProduct,
            getCartTotalPrice = mockGetCartTotalPrice,
            getCartProducts = mockGetCartProducts,
            progressCentricNotificationManager = progressCentricNotificationManager
        )
    }

    @Test
    fun `when addToCard called with valid id and product is found in cartProducts, then addToCart useCase should be invoked`() =
        runTest {
            // Given
            val id = "123"

            val testItem = CartItem(
                id = id,
                name = "Test",
                description = "Desc",
                imageURL = "img",
                price = 10.0,
                priceText = "₺10",
                quantity = 1
            )

            val expectedProduct = ProductItem(
                id = testItem.id,
                name = testItem.name,
                description = testItem.description,
                imageUrl = testItem.imageURL,
                price = testItem.price,
                priceText = testItem.priceText
            )

            viewModel.updateCartProducts(cartProducts = listOf(testItem))

            // When
            viewModel.addToCard(id)

            // Then
            coVerify { mockAddToCart.invoke(expectedProduct) }
        }

    @Test
    fun `when addToCard called with valid id and product is found in suggestedProducts, then addToCart useCase should be invoked`() =
        runTest {
            // Given
            val id = "123"

            val expectedProduct = ProductItem(
                id = id,
                name = "Test",
                description = "Desc",
                imageUrl = "img",
                price = 10.0,
                priceText = "₺10",
                quantity = 1
            )

            viewModel.updateSuggestedProduct(suggestedProducts = listOf(expectedProduct))

            // When
            viewModel.addToCard(id)

            // Then
            coVerify { mockAddToCart.invoke(expectedProduct) }
        }

    @Test
    fun `when addToCard called with unknown id, then addToCart should NOT be invoked`() = runTest {
        //Given
        val id = "non-existent-id"

        // When
        viewModel.addToCard(id)

        // Then
        coVerify(exactly = 0) { mockAddToCart.invoke(any()) }
    }

    @Test
    fun `when removeFromCard is called with id, then removeFromCart useCase should be invoked with same id`() =
        runTest {
            // Given
            val id = "id"

            // When
            viewModel.removeFromCard(id)

            // Then
            coVerify { mockRemoveFromCart.invoke(id = id) }
        }

    @Test
    fun `when navigateToDetail is called with id and product is found in cartProducts, then navigateToDetail should be called with that product`() =
        runTest {            // Given
            val id = "123"

            val testItem = CartItem(
                id = id,
                name = "Test",
                description = "Desc",
                imageURL = "img",
                price = 10.0,
                priceText = "₺10",
                quantity = 1
            )

            val expectedProduct = ProductItem(
                id = testItem.id,
                name = testItem.name,
                description = testItem.description,
                imageUrl = testItem.imageURL,
                price = testItem.price,
                priceText = testItem.priceText
            )

            viewModel.updateCartProducts(cartProducts = listOf(testItem))

            // When
            viewModel.navigateToDetail(id)

            // Then
            coVerify { mockNavigation.navigateToDetail(productItem = expectedProduct) }
        }

    @Test
    fun `when navigateToDetail is called with id and product is found in suggestedProducts, then navigateToDetail should be called with that product`() =
        runTest {
            // Given
            val id = "123"

            val expectedProduct = ProductItem(
                id = id,
                name = "Test",
                description = "Desc",
                imageUrl = "img",
                price = 10.0,
                priceText = "₺10",
                quantity = 1
            )

            viewModel.updateSuggestedProduct(suggestedProducts = listOf(expectedProduct))

            // When
            viewModel.navigateToDetail(id)

            // Then
            coVerify { mockNavigation.navigateToDetail(productItem = expectedProduct) }
        }

    @Test
    fun `when updateCartProducts is called, then cartProducts in uiState should be updated`() =
        runTest {
            // Given
            val cartItems = listOf(
                CartItem(
                    id = "1",
                    name = "Test",
                    description = "desc",
                    imageURL = "image",
                    price = 9.99,
                    priceText = "₺9,99",
                    quantity = 0
                )
            )

            // When
            viewModel.updateCartProducts(cartItems)

            // Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertEquals(cartItems, state.cartProducts)
                verify(exactly = 0) { mockNavigation.popBackStackToProductList() }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when updateCartProducts is called with empty list, then should navigate to product list`() =
        runTest {
            // Given
            val emptyList = emptyList<CartItem>()

            // When
            viewModel.updateCartProducts(emptyList)

            // Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertTrue(state.cartProducts.isEmpty())
                verify(exactly = 1) { mockNavigation.popBackStackToProductList() }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when updateCartTotalPrice is called, then totalPriceFormatted in uiState should be updated`() =
        runTest {
            // Given
            val expectedPrice = "₺45,00"

            // When
            viewModel.updateCartTotalPrice(expectedPrice)

            // Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertEquals(expectedPrice, state.totalPriceFormatted)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when navigateUp is called, then navigation navigateUp should be triggered`() = runTest {
        // When
        viewModel.navigateUp()

        // Then
        verify { mockNavigation.navigateUp() }
    }
}
