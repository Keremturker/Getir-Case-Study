package com.kturker.feature.product.presentation.list

import app.cash.turbine.test
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.domain.usecase.AddToCartUseCase
import com.kturker.core.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.core.domain.usecase.RemoveFromCartUseCase
import com.kturker.feature.product.domain.usecase.ProductPagingUseCase
import com.kturker.feature.product.domain.usecase.RefreshProductsUseCase
import com.kturker.feature.product.domain.usecase.SuggestedProductPagingUseCase
import com.kturker.feature.product.presentation.navigation.ProductNavigation
import com.kturker.language.StringResourceManager
import com.kturker.language.StringResourcesUiModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.reflect.KProperty1

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class ProductListViewmodelTest {

    @MockK
    private lateinit var mockRefreshProducts: RefreshProductsUseCase

    @MockK
    private lateinit var mockAddToCart: AddToCartUseCase

    @MockK
    private lateinit var mockRemoveFromCart: RemoveFromCartUseCase

    @MockK
    private lateinit var mockNavigation: ProductNavigation

    @MockK
    private lateinit var mockStringResourceManager: StringResourceManager

    @MockK
    private lateinit var mockProductPaging: ProductPagingUseCase

    @MockK
    private lateinit var mockSuggestedProductPaging: SuggestedProductPagingUseCase

    @MockK
    private lateinit var mockGetCartTotalPrice: GetCartTotalPriceUseCase

    private lateinit var viewModel: ProductListViewmodel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every {
            mockStringResourceManager[any<KProperty1<StringResourcesUiModel, String>>()]
        } answers { firstArg<KProperty1<StringResourcesUiModel, String>>().toString() }

        every {
            mockProductPaging.invoke()
        } returns flowOf()

        every {
            mockSuggestedProductPaging.invoke()
        } returns flowOf()

        every {
            mockGetCartTotalPrice.invoke()
        } returns flowOf()

        viewModel = ProductListViewmodel(
            refreshProducts = mockRefreshProducts,
            addToCart = mockAddToCart,
            removeFromCart = mockRemoveFromCart,
            navigation = mockNavigation,
            stringResourceManager = mockStringResourceManager,
            productPaging = mockProductPaging,
            suggestedProductPaging = mockSuggestedProductPaging,
            getCartTotalPrice = mockGetCartTotalPrice
        )
    }

    @AfterEach
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `when navigateToDetailScreen called, then navigation should be triggered with item`() =
        runTest {
            //Given
            val item = ProductItem(
                id = "1",
                name = "Test",
                description = "",
                imageUrl = "",
                price = 0.0,
                priceText = ""
            )

            //When
            viewModel.navigateToDetailScreen(item)

            //Then
            verify { mockNavigation.navigateToDetailScreen(item) }
        }


    @Test
    fun `when navigateToCartScreen is called, then should trigger navigation to cart screen`() =
        runTest {
            //When
            viewModel.navigateToCartScreen()

            //Then
            verify { mockNavigation.navigateToCartScreen() }
        }

    @Test
    fun `given a valid product item, when addToCart is called, then should invoke use case with correct item`() =
        runTest {
            //Given
            val item = ProductItem(
                id = "1",
                name = "Doritos",
                description = "Test",
                imageUrl = "",
                price = 10.0,
                priceText = "₺10,00"
            )

            //When
            viewModel.addToCart(item)

            //Then
            coVerify { mockAddToCart.invoke(item) }
        }

    @Test
    fun `given a product id, when removeFromCart is called, then should invoke use case with correct id`() =
        runTest {
            //Given
            val id = "99"

            val item = ProductItem(
                id = id,
                name = "Ruffles",
                description = "Test",
                imageUrl = "",
                price = 15.0,
                priceText = "₺15,00"
            )

            //When
            viewModel.removeFromCart(item)

            //Then
            coVerify { mockRemoveFromCart.invoke(id) }
        }

    @Test
    fun `given defaultOnLoading is true, when onFetchData is called, then uiState should have loading and refreshing true initially`() =
        runTest {
            // Given
            val flow = flowOf<String?>()
            coEvery { mockRefreshProducts.invoke() } returns flow

            val expectedOnLoading = true
            val expectedIsRefreshing = true

            // When
            viewModel.onFetchData(expectedOnLoading)

            // Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertEquals(expectedIsRefreshing, state.isLoading)
                assertEquals(expectedOnLoading, state.isRefreshing)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given refreshProducts returns non-null error, when onFetchData is called, then loading and refreshing should be false`() =
        runTest {
            //Given
            val errorMessage = "Error"
            coEvery { mockRefreshProducts.invoke() } returns flowOf(errorMessage)

            val defaultOnLoading = true

            //When
            viewModel.onFetchData(defaultIsRefreshing = defaultOnLoading)

            //Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertFalse(state.isLoading)
                assertFalse(state.isRefreshing)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given refreshProducts returns null, when onFetchData is called, then loading and refreshing should be false`() =
        runTest {
            //Given
            val errorMessage: String? = null
            coEvery { mockRefreshProducts.invoke() } returns flowOf(errorMessage)

            val defaultOnLoading = true

            //When
            viewModel.onFetchData(defaultIsRefreshing = defaultOnLoading)

            //Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertFalse(state.isLoading)
                assertFalse(state.isRefreshing)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given new price, when updateTotalPrice is called, then uiState should contain updated totalPriceFormatted`() =
        runTest {
            // Given
            val expectedPrice = "₺99.99"

            // When
            viewModel.updateTotalPrice(totalPrice = expectedPrice)

            // Then
            viewModel.uiState.test {
                val state = awaitItem()
                assertEquals(expectedPrice, state.totalPriceFormatted)
                cancelAndIgnoreRemainingEvents()
            }
        }
}