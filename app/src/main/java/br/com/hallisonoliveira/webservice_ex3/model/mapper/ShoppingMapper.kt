package br.com.hallisonoliveira.webservice_ex3.model.mapper

import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import br.com.hallisonoliveira.webservice_ex3.model.remote.ShoppingRemote

fun ShoppingRemote.toDomain() : Shopping {
    return Shopping(
        id = id,
        name = name,
        amount = amount,
        brand = brand,
        shelfLife = shelfLife
    )
}

fun Shopping.toRemote() : ShoppingRemote {
    return ShoppingRemote(
        id = id ?: "",
        name = name,
        amount = amount,
        brand = brand,
        shelfLife = shelfLife
    )
}