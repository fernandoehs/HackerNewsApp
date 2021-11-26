package com.fernandoherrera.hackernewsapp.di

import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsLocalDataSource
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsLocalDataSourceImpl
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsRemoteDataSource
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<HackerNewsLocalDataSource> {
        HackerNewsLocalDataSourceImpl(
            database = get()
        )
    }

    factory<HackerNewsRemoteDataSource> {
        HackerNewsRemoteDataSourceImpl(
            service = get()
        )
    }
}
