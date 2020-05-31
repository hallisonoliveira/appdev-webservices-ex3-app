package br.com.hallisonoliveira.webservice_ex3.interactor

import br.com.hallisonoliveira.webservice_ex3.repository.ShoppingRepository

class DeleteShoppingInteractor(
    private val repository: ShoppingRepository
) {

    suspend fun execute(id: String) = repository.delete(id)

}