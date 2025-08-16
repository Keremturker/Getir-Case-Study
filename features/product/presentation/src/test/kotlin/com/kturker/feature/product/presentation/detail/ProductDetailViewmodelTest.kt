package com.kturker.feature.product.presentation.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.domain.usecase.AddToCartUseCase
import com.kturker.core.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.core.domain.usecase.RemoveFromCartUseCase
import com.kturker.core.presentation.KEY_ARGS
import com.kturker.core.presentation.putJson
import com.kturker.feature.product.contract.ProductDetailArgs
import com.kturker.feature.product.domain.usecase.GetQuantityByIdUseCase
import com.kturker.feature.product.presentation.navigation.ProductNavigation
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class ProductDetailViewmodelTest {

    @MockK
    private lateinit var mockNavigation: ProductNavigation

    @MockK
    private lateinit var mockAddToCart: AddToCartUseCase

    @MockK
    private lateinit var mockRemoveFromCart: RemoveFromCartUseCase

    @MockK
    private lateinit var mockGetCartTotalPrice: GetCartTotalPriceUseCase

    @MockK
    private lateinit var mockGetQuantityById: GetQuantityByIdUseCase

    private lateinit var viewModel: ProductDetailViewModel

    private val args = ProductDetailArgs(
        id = "1",
        name = "Doritos Risk",
        description = "Süper Baharatlı",
        imageUrl = "https://example.com/image.jpg",
        price = 29.99,
        priceText = "₺29,99",
        isCameFromCart = false
    )

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        val handle = SavedStateHandle()
        handle.putJson(KEY_ARGS, args)

        every {
            mockGetCartTotalPrice.invoke()
        } returns flowOf()

        every {
            mockGetQuantityById.invoke(id = args.id)
        } returns flowOf()

        viewModel = ProductDetailViewModel(
            savedStateHandle = handle,
            navigation = mockNavigation,
            addToCart = mockAddToCart,
            removeFromCart = mockRemoveFromCart,
            getCartTotalPrice = mockGetCartTotalPrice,
            getQuantityById = mockGetQuantityById
        )
    }

    @Test
    fun `when navigateUp is called, then navigation navigateUp should be invoked`() = runTest {
        // When
        viewModel.navigateUp()

        // Then
        verify { mockNavigation.navigateUp() }
    }

    @Test
    fun `when addToCart is called, then use case should be invoked with correct ProductItem`() =
        runTest {
            //When
            viewModel.addToCart()

            //Then
            coVerify {
                mockAddToCart.invoke(
                    ProductItem(
                        id = args.id,
                        name = args.name,
                        description = args.description,
                        imageUrl = args.imageUrl,
                        price = args.price,
                        priceText = args.priceText
                    )
                )
            }
        }

    @Test
    fun `when removeFromCard is called, then use case should be invoked with correct id`() =
        runTest {
            //When
            viewModel.removeFromCart()

            //Then
            coVerify { mockRemoveFromCart.invoke(id = args.id) }
        }

    @Test
    fun `when navigateToCartScreen is called, then should navigate to cart screen`() {
        //When
        viewModel.navigateToCartScreen()

        //Then
        verify { mockNavigation.navigateToCartScreen() }
    }

    @Test
    fun `when updateCartTotalPrice called, then should update uiState with given total price`() =
        runTest {
            //Given
            val price = "₺49.90"

            //When
            viewModel.updateCartTotalPrice(price)

            //Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertEquals(price, state.totalPriceFormatted)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when updateProductQuantity called, then should update uiState with given value`() =
        runTest {
            //Given
            val quantity = 3

            //When
            viewModel.updateProductQuantity(quantity)

            //Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertEquals(quantity, state.productQuantity)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
