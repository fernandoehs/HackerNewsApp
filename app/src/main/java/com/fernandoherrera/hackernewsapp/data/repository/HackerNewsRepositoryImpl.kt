package com.fernandoherrera.hackernewsapp.data.repository

import androidx.paging.*
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsLocalDataSource
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsRemoteDataSource
import com.fernandoherrera.hackernewsapp.data.mapper.MapHitEntityToDomain
import com.fernandoherrera.hackernewsapp.data.paging.HackerNewRemoteMediator
import com.fernandoherrera.hackernewsapp.domain.model.Hit
import com.fernandoherrera.hackernewsapp.domain.repository.HackerNewsRepository
import com.fernandoherrera.hackernewsapp.utils.HIT_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class HackerNewsRepositoryImpl(
    private val hackerNewsLocalDataSource: HackerNewsLocalDataSource,
    private val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource,
    private val mapHitEntityToDomain: MapHitEntityToDomain
) :
    HackerNewsRepository {


    override fun allHits(query: String?): Flow<PagingData<Hit>> {
        val pagingSourceFactory = { hackerNewsLocalDataSource.getLocalHits(query) }

        return Pager(
            config = PagingConfig(
                pageSize = HIT_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = HackerNewRemoteMediator(
                remoteDataSource = hackerNewsRemoteDataSource,
                localDataSource = hackerNewsLocalDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map {
                mapHitEntityToDomain.map(it)
            }
        }
    }

    override suspend fun removeItemById(hitObjectId: String): Boolean {
        return hackerNewsLocalDataSource.removeItemById(hitObjectId)
    }
}
