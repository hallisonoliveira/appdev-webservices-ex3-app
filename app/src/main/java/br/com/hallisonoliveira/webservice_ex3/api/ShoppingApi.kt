package br.com.hallisonoliveira.webservice_ex3.api

import br.com.hallisonoliveira.webservice_ex3.model.remote.ShoppingRemote
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoppingApi {

    @GET("api/shopping")
    suspend fun list() : List<ShoppingRemote>

    @DELETE("api/shopping/{id}")
    suspend fun delete(
        @Path("id") id: String)

}