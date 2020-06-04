package br.com.hallisonoliveira.webservice_ex3

import android.app.Application
import br.com.hallisonoliveira.webservice_ex3.di.androidModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

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