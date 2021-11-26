package com.fernandoherrera.hackernewsapp.di

import androidx.paging.ExperimentalPagingApi
import com.fernandoherrera.hackernewsapp.data.repository.HackerNewsRepositoryImpl
import com.fernandoherrera.hackernewsapp.domain.repository.HackerNewsRepository
import org.koin.dsl.module

@ExperimentalPagingApi
val repositoryModule = module {
    factory<HackerNewsRepository> {
        HackerNewsRepositoryImpl(
            hackerNewsLocalDataSource = get(),
            hackerNewsRemoteDataSource = get(),
            mapHitEntityToDomain = get()
        )
    }
}
