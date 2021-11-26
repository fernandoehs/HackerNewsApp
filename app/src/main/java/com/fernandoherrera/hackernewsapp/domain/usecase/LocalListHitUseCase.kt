package com.fernandoherrera.hackernewsapp.domain.usecase

import com.fernandoherrera.hackernewsapp.domain.repository.HackerNewsRepository

class LocalListHitUseCase(private val repository: HackerNewsRepository) {
    operator fun invoke(query: String?) = repository.allHits(query)
}
