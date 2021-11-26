package com.fernandoherrera.hackernewsapp

import android.app.Application
import androidx.core.content.res.ResourcesCompat
import androidx.paging.ExperimentalPagingApi
import com.fernandoherrera.hackernewsapp.di.dataSourceModule
import com.fernandoherrera.hackernewsapp.di.domainModule
import com.fernandoherrera.hackernewsapp.di.localStorageModule
import com.fernandoherrera.hackernewsapp.di.mapperModule
import com.fernandoherrera.hackernewsapp.di.networkModule
import com.fernandoherrera.hackernewsapp.di.repositoryModule
import com.fernandoherrera.hackernewsapp.di.viewModelModule
import es.dmoral.toasty.Toasty
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HackerNewsApp : Application() {
    @ExperimentalPagingApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@HackerNewsApp)
            modules(
                dataSourceModule,
                domainModule,
                localStorageModule,
                mapperModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
        setupToastyParams()
    }

    private fun setupToastyParams() {
        Toasty.Config.getInstance().allowQueue(false).apply()
    }
}
