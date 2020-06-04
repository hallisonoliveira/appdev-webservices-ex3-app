package br.com.hallisonoliveira.webservice_ex3.api

import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import br.com.hallisonoliveira.webservice_ex3.model.remote.ShoppingRemote
import retrofit2.http.*

interface ShoppingApi {

    @GET("api/shopping")
    suspend fun list() : List<Shopping>

    @DELETE("api/shopping/{id}")
    suspend fun delete(
        @Path("id") id: String)

    @GET("api/shopping/{id}")
    suspend fun get(
        @Path("id") id: String
    ) : ShoppingRemote

    @POST("api/shopping")
    suspend fun add(
        @Body shopping: Shopping
    )

    @PUT("api/shopping")
    suspend fun update(
        @Body shopping: Shopping
    )
}