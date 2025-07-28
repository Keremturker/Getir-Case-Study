package com.kturker.language.resources

import com.kturker.language.LanguageUiModel
import com.kturker.language.StringResourcesUiModel

internal val resourceFR = LanguageUiModel(
    language = LanguageType.FR.code,
    resources = StringResourcesUiModel(
        productListTitle = "Produits",
        goToCartButtonTitle = "Aller au panier",
        emptyListText = "Aucun produit trouvé",
        productDetailTitle = "Détail du produit",
        cartTitle = "Mon panier",
        completeOrderTitle = "Finaliser la commande",
        suggestedProductListTitle = "Produits suggérés",
        addToCartTitle = "Ajouter au panier",
        completeOrderDialogDescription = "Votre commande d’un total de {0} a été effectuée avec succès.",
        clearCartDialogDescription = "Tous les articles seront supprimés du panier. Voulez-vous continuer ?",
        yes = "Oui",
        abort = "Annuler",
        close = "Fermer",
        orderIsPlaced = "Votre commande a été passée",
        orderIsConfirming = "Confirmation en cours",
        orderIsPreparing = "Votre commande est en cours de préparation",
        orderIsPreparingToDelivery = "Prochaine étape : la livraison",
        orderIsOnTheWay = "Votre commande est en route",
        orderIsEnroute = "En chemin vers l'adresse de livraison",
        orderIsArriving = "Votre commande est sur le point d'arriver...",
        orderIsEnjoy = "Bon appétit 😋",
        orderIsDelivered = "Votre commande est terminée.",
        orderIsThanks = "Merci d’avoir utilisé notre application."
    )
)
