package br.com.hallisonoliveira.webservice_ex3.model.remote

data class ShoppingRemote (
    val id: String,
    val name: String,
    val amount: Int,
    val brand: String,
    val shelfLife: String
)