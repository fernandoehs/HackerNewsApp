package com.fernandoherrera.hackernewsapp.di

import com.fernandoherrera.hackernewsapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(localListHitUseCase = get(), removeHitUseCase = get(), mapHitToItem = get())
    }
}
