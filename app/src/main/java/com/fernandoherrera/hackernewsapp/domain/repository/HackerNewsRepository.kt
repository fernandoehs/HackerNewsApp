package com.fernandoherrera.hackernewsapp.domain.repository

import androidx.paging.PagingData
import com.fernandoherrera.hackernewsapp.domain.model.Hit
import kotlinx.coroutines.flow.Flow

interface HackerNewsRepository {
    fun allHits(query: String?): Flow<PagingData<Hit>>
    suspend fun removeItemById(hitObjectId: String): Boolean
}
