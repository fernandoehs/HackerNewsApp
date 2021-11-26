package com.fernandoherrera.hackernewsapp.domain.usecase

import com.fernandoherrera.hackernewsapp.domain.repository.HackerNewsRepository

class RemoveHitUseCase(private val repository: HackerNewsRepository) {
    suspend operator fun invoke(hitObjectId: String) = repository.removeItemById(hitObjectId)
}
