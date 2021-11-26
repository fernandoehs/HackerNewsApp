package com.fernandoherrera.hackernewsapp.data.api

import com.fernandoherrera.hackernewsapp.data.model.remote.HitResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsService {

    @GET("search_by_date")
    suspend fun searchByDate(@Query("query") query: String, @Query("page") page: Int): HitResponse
}
