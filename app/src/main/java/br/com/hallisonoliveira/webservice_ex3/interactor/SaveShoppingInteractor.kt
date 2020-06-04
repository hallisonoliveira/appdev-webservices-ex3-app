package br.com.hallisonoliveira.webservice_ex3.interactor

import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import br.com.hallisonoliveira.webservice_ex3.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveShoppingInteractor (
    private val repository: ShoppingRepository
) {

    suspend fun save(shopping: Shopping) = withContext(Dispatchers.IO) {
        repository.save(shopping)
    }

}