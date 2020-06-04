package br.com.hallisonoliveira.webservice_ex3.interactor

import br.com.hallisonoliveira.webservice_ex3.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetShoppingInteractor (
    private val repository: ShoppingRepository
) {

    suspend fun get(id: String) = withContext(Dispatchers.IO) {
        repository.get(id)
    }

}