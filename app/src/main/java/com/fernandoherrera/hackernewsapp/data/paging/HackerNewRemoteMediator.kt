package com.fernandoherrera.hackernewsapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsLocalDataSource
import com.fernandoherrera.hackernewsapp.data.datasource.HackerNewsRemoteDataSource
import com.fernandoherrera.hackernewsapp.data.model.local.HitEntity
import com.fernandoherrera.hackernewsapp.data.model.local.RemoteKeysEntity
import com.fernandoherrera.hackernewsapp.data.model.local.RemovedHitEntity
import com.fernandoherrera.hackernewsapp.data.wrapper.Resource
import com.fernandoherrera.hackernewsapp.utils.DEFAULT_QUERY
import com.fernandoherrera.hackernewsapp.utils.DateUtil

@OptIn(ExperimentalPagingApi::class)
class HackerNewRemoteMediator(
    private val remoteDataSource: HackerNewsRemoteDataSource,
    private val localDataSource: HackerNewsLocalDataSource
) : RemoteMediator<Int, HitEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HitEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                null
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                remoteKeys.nextKey
            }

            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
        }
        try {
            val currentPage = page ?: 1
            val hitResponse = remoteDataSource.searchByDate(DEFAULT_QUERY, currentPage)
            if (hitResponse is Resource.Success) {
                val hitsDto = hitResponse.data.hitDtos.toMutableList()
                val endOfPaginationReached = hitsDto.isEmpty()

                localDataSource.doOperationInTransaction {
                    // clear all tables in the database
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.clearRemoteKeys()
                        localDataSource.clearLocalHits()
                    }
                    val nextKey = if (endOfPaginationReached) null else currentPage + 1

                    val removedHits = localDataSource.getRemotedHits()
                    val remotedKeyEntities = mutableListOf<RemoteKeysEntity>()
                    val hitEntities = mutableListOf<HitEntity>()
                    hitsDto.filter { hitDto ->
                        val title = hitDto.storyTitle ?: hitDto.title ?: ""
                        (
                            removedHits.contains(RemovedHitEntity(hitDto.objectID))
                                .not() && title.isNotEmpty() && hitDto.storyUrl.isNullOrEmpty().not()
                            )
                    }.forEach { hitsDtoMap ->
                        remotedKeyEntities.add(
                            RemoteKeysEntity(
                                hitsDtoMap.objectID,
                                nextKey = nextKey
                            )
                        )
                        hitEntities.add(
                            HitEntity(
                                hitsDtoMap.objectID,
                                hitsDtoMap.storyTitle,
                                hitsDtoMap.author,
                                DateUtil.fromStringToDateTime(hitsDtoMap.createdAt),
                                hitsDtoMap.storyUrl
                            )
                        )
                    }
                    localDataSource.insertRemoteKeys(remotedKeyEntities)
                    localDataSource.insertHits(hitEntities)
                }
                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else return MediatorResult.Error(Exception((hitResponse as Resource.Error).errorMessage))
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, HitEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                localDataSource.remoteKeyById(it.objectId)
            }
    }
}
