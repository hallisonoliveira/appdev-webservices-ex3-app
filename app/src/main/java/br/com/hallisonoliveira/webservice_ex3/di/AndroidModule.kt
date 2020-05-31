package br.com.hallisonoliveira.webservice_ex3.di

import br.com.hallisonoliveira.webservice_ex3.api.ShoppingApi
import br.com.hallisonoliveira.webservice_ex3.interactor.DeleteShoppingInteractor
import br.com.hallisonoliveira.webservice_ex3.interactor.ListShoppingInteractor
import br.com.hallisonoliveira.webservice_ex3.repository.ShoppingRepository
import br.com.hallisonoliveira.webservice_ex3.ui.list.ListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val androidModule = module {
    single { this }

    viewModel {
        ListViewModel(
            listShoppingInteractor = get(),
            deleteShoppingInteractor = get()
        )
    }

    factory {
        ListShoppingInteractor(repository = get())
    }

    factory {
        DeleteShoppingInteractor(repository = get())
    }

    factory {
        ShoppingRepository(api = get())
    }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient
            .addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.25.146:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()

        retrofit.create(ShoppingApi::class.java)
    }
}