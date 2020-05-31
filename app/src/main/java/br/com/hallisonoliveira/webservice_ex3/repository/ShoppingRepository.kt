package br.com.hallisonoliveira.webservice_ex3.repository

import br.com.hallisonoliveira.webservice_ex3.api.ShoppingApi
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import br.com.hallisonoliveira.webservice_ex3.model.mapper.toDomain

class ShoppingRepository(
    private val api: ShoppingApi
) {

    suspend fun list() : List<Shopping> {
        return api.list().map { it.toDomain() }
    }

    suspend fun delete(id: String) {
        api.delete(id)
    }

}