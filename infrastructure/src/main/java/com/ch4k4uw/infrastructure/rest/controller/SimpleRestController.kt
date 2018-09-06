package com.ch4k4uw.infrastructure.rest.controller

import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import com.ch4k4uw.infrastructure.abstraction.rest.Movie
import com.ch4k4uw.infrastructure.abstraction.rest.controller.RestController
import com.ch4k4uw.infrastructure.abstraction.rest.MovieDetail
import com.ch4k4uw.infrastructure.abstraction.rest.MovieType
import com.ch4k4uw.infrastructure.rest.OmdbApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

class SimpleRestController @Inject constructor(@Named("restUrl") baseUri: String, @Named("apiKey") private val apiKey: String, private val scheduler: SchedulerProvider): RestController {
    private val api: OmdbApi

    private val movieTypesDataSource: List<MovieType> = listOf(
            MovieType(1, "episode"),
            MovieType(2, "game"),
            MovieType(3, "movie"),
            MovieType(4, "series")
    )

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit
                .Builder()
                .baseUrl(baseUri)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(OmdbApi::class.java)

    }

    override fun getMovieTypes(success: (List<MovieType>) -> Unit, error: (Throwable) -> Unit) {
        success(movieTypesDataSource)
    }

    override fun getMovieType(code: Int, success: (MovieType) -> Unit, error: (Throwable) -> Unit) {
        var found = false
        movieTypesDataSource.forEach {
            if(it.id == code) {
                success(it)
                found = true
            }
        }

        if(!found) {
            error(Exception("Not found."))
        }
    }

    override fun getMovieType(name: String, success: (MovieType) -> Unit, error: (Throwable) -> Unit) {
        var found = false
        movieTypesDataSource.forEach {
            if(it.name.toLowerCase() == name.toLowerCase()) {
                success(it)
                found = true
            }
        }

        if(!found) {
            error(Exception("Not found."))
        }
    }

    override fun searchMovies(title: String, type: String?, year: String?, page: Int, success: (List<Movie>) -> Unit, error: (Throwable) -> Unit) {
        api.searchMovies(apiKey, title, type, year, page)
                .map {
                    it.Search ?: listOf()
                }
                .compose(scheduler.applySchedulers())
                .subscribe(success, error)
    }

    override fun getById(id: String, success: (MovieDetail) -> Unit, error: (Throwable) -> Unit) {
        api.getById(apiKey, id)
                .compose(scheduler.applySchedulers())
                .subscribe(success) {
                    error(it)
                }
    }

}