package br.com.hallisonoliveira.webservice_ex3.interactor

import br.com.hallisonoliveira.webservice_ex3.repository.ShoppingRepository

class ListShoppingInteractor(
    private val repository: ShoppingRepository
) {

    suspend fun list() = repository.list()

}