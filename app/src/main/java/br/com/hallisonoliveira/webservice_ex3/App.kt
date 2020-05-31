package br.com.hallisonoliveira.webservice_ex3

import android.app.Application
import br.com.hallisonoliveira.webservice_ex3.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(androidModule)
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}