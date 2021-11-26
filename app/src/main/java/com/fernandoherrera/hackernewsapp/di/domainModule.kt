package com.fernandoherrera.hackernewsapp.di

import com.fernandoherrera.hackernewsapp.domain.usecase.LocalListHitUseCase
import com.fernandoherrera.hackernewsapp.domain.usecase.RemoveHitUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        LocalListHitUseCase(repository = get())
    }

    factory {
        RemoveHitUseCase(repository = get())
    }
}
