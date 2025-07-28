package com.kturker.language.resources

import com.kturker.language.LanguageUiModel
import com.kturker.language.StringResourcesUiModel

internal val resourceEN = LanguageUiModel(
    language = LanguageType.EN.code,
    resources = StringResourcesUiModel(
        productListTitle = "Products",
        goToCartButtonTitle = "Go to Cart",
        emptyListText = "No products found",
        productDetailTitle = "Product Detail",
        cartTitle = "My Cart",
        completeOrderTitle = "Complete Order",
        suggestedProductListTitle = "Suggested Products",
        addToCartTitle = "Add to Cart",
        completeOrderDialogDescription = "Your order totaling {0} has been successfully placed.",
        clearCartDialogDescription = "All items will be removed from the cart. Do you want to continue?",
        yes = "Yes",
        abort = "Cancel",
        close = "Close",
        orderIsPlaced = "Your order has been placed",
        orderIsConfirming = "Confirming",
        orderIsPreparing = "Your order is being prepared",
        orderIsPreparingToDelivery = "Next step is delivery",
        orderIsOnTheWay = "Your order is on the way",
        orderIsEnroute = "Heading to the delivery address",
        orderIsArriving = "Your order is arriving...",
        orderIsEnjoy = "Enjoy 😋",
        orderIsDelivered = "Your order is complete.",
        orderIsThanks = "Thank you for using our app."
    )
)
