package com.fernandoherrera.hackernewsapp.data.datasource

import com.fernandoherrera.hackernewsapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewsapp.data.wrapper.Resource

interface HackerNewsRemoteDataSource {
    suspend fun searchByDate(query: String?, page: Int): Resource<HitResponse>
}
