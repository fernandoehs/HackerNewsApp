package com.fernandoherrera.hackernewsapp.di

import com.fernandoherrera.hackernewsapp.data.mapper.MapHitDtoToEntity
import com.fernandoherrera.hackernewsapp.data.mapper.MapHitEntityToDomain
import com.fernandoherrera.hackernewsapp.ui.main.mapper.MapHitToItem
import org.koin.dsl.module

val mapperModule = module {
    single { MapHitEntityToDomain() }
    single { MapHitDtoToEntity() }
    single { MapHitToItem() }
}
