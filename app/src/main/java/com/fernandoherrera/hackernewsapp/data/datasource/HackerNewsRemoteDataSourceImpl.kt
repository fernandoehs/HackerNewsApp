package com.fernandoherrera.hackernewsapp.data.datasource

import com.fernandoherrera.hackernewsapp.data.api.HackerNewsService
import com.fernandoherrera.hackernewsapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewsapp.data.networking.RemoteCallHelper
import com.fernandoherrera.hackernewsapp.data.wrapper.Resource
import com.fernandoherrera.hackernewsapp.utils.DEFAULT_QUERY

class HackerNewsRemoteDataSourceImpl(
    private val service: HackerNewsService,
) : HackerNewsRemoteDataSource, RemoteCallHelper() {

    override suspend fun searchByDate(query: String?, page: Int): Resource<HitResponse> {
        return safeApiCall { service.searchByDate(query ?: DEFAULT_QUERY, page) }
    }
}
