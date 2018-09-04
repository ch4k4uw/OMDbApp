package com.ch4k4uw.infrastructure.rest

import com.ch4k4uw.infrastructure.abstraction.rest.MovieDetail
import com.ch4k4uw.infrastructure.abstraction.rest.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET(".")
    fun searchMovies(@Query("apiKey") apiKey: String, @Query("s") title: String, @Query("type") type: String?, @Query("y") year: String?, @Query("page") page: Int): Observable<SearchResult>

    @GET(".")
    fun getById(@Query("apiKey") apiKey: String, @Query("i") id: String): Observable<MovieDetail>

}