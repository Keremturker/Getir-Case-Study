package com.kturker.language.resources

import com.kturker.language.LanguageUiModel
import com.kturker.language.StringResourcesUiModel

internal val resourceIT = LanguageUiModel(
    language = LanguageType.IT.code,
    resources = StringResourcesUiModel(
        productListTitle = "Prodotti",
        goToCartButtonTitle = "Vai al carrello",
        emptyListText = "Nessun prodotto trovato",
        productDetailTitle = "Dettagli prodotto",
        cartTitle = "Il mio carrello",
        completeOrderTitle = "Completa ordine",
        suggestedProductListTitle = "Prodotti suggeriti",
        addToCartTitle = "Aggiungi al carrello",
        completeOrderDialogDescription = "Il tuo ordine per un totale di {0} è stato completato con successo.",
        clearCartDialogDescription = "Tutti gli articoli verranno rimossi dal carrello. Vuoi continuare?",
        yes = "Sì",
        abort = "Annulla",
        close = "Chiudi",
        orderIsPlaced = "Il tuo ordine è stato effettuato",
        orderIsConfirming = "Conferma in corso",
        orderIsPreparing = "Il tuo ordine è in preparazione",
        orderIsPreparingToDelivery = "Prossimo passo: consegna",
        orderIsOnTheWay = "Il tuo ordine è in arrivo",
        orderIsEnroute = "In viaggio verso l’indirizzo di consegna",
        orderIsArriving = "Il tuo ordine sta arrivando...",
        orderIsEnjoy = "Buon appetito 😋",
        orderIsDelivered = "Il tuo ordine è completato.",
        orderIsThanks = "Grazie per aver usato la nostra app."
    )
)
