package com.kturker.language.resources

import com.kturker.language.LanguageUiModel
import com.kturker.language.StringResourcesUiModel

internal val resourceES = LanguageUiModel(
    language = LanguageType.ES.code,
    resources = StringResourcesUiModel(
        productListTitle = "Productos",
        goToCartButtonTitle = "Ir al carrito",
        emptyListText = "No se encontraron productos",
        productDetailTitle = "Detalle del producto",
        cartTitle = "Mi carrito",
        completeOrderTitle = "Completar pedido",
        suggestedProductListTitle = "Productos sugeridos",
        addToCartTitle = "Agregar al carrito",
        completeOrderDialogDescription = "Tu pedido por un total de {0} ha sido realizado con éxito.",
        clearCartDialogDescription = "Todos los artículos serán eliminados del carrito. ¿Deseas continuar?",
        yes = "Sí",
        abort = "Cancelar",
        close = "Cerrar",
        orderIsPlaced = "Tu pedido ha sido realizado",
        orderIsConfirming = "Confirmando",
        orderIsPreparing = "Tu pedido está siendo preparado",
        orderIsPreparingToDelivery = "El siguiente paso es la entrega",
        orderIsOnTheWay = "Tu pedido está en camino",
        orderIsEnroute = "En ruta a la dirección de entrega",
        orderIsArriving = "Tu pedido está por llegar...",
        orderIsEnjoy = "¡Disfrútalo 😋!",
        orderIsDelivered = "Tu pedido está completo.",
        orderIsThanks = "Gracias por usar nuestra app."
    )
)
