package com.fernandoherrera.hackernewsapp.di

import com.fernandoherrera.hackernewsapp.data.database.HackerNewsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localStorageModule = module {
    single { HackerNewsDatabase.getInstance(androidContext()) }
}
