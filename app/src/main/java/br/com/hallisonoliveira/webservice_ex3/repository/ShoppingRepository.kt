package br.com.hallisonoliveira.webservice_ex3.repository

import br.com.hallisonoliveira.webservice_ex3.api.ShoppingApi
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import br.com.hallisonoliveira.webservice_ex3.model.mapper.toDomain

class ShoppingRepository(
    private val api: ShoppingApi
) {

    suspend fun list() : List<Shopping> {
        return api.list()
    }

    suspend fun delete(id: String) {
        api.delete(id)
    }

    suspend fun get(id: String) : Shopping {
        return api.get(id).toDomain()
    }

    suspend fun save(shopping: Shopping) {
        if (shopping.id.isEmpty()) {
            add(shopping)
        } else {
            update(shopping)
        }
    }

    private suspend fun add(shopping: Shopping) {
        api.add(shopping)
    }

    private suspend fun update(shopping: Shopping) {
        api.update(shopping)
    }

}