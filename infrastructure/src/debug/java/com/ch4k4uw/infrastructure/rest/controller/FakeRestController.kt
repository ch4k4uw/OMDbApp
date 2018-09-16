package com.ch4k4uw.infrastructure.rest.controller

import android.content.Context
import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import com.ch4k4uw.infrastructure.abstraction.rest.Movie
import com.ch4k4uw.infrastructure.abstraction.rest.MovieDetail
import com.ch4k4uw.infrastructure.abstraction.rest.MovieType
import com.ch4k4uw.infrastructure.abstraction.rest.SearchResult
import com.ch4k4uw.infrastructure.abstraction.rest.controller.RestController
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import java.nio.charset.Charset
import javax.inject.Inject

private data class SearchResults(val results: List<SearchResult>)
private fun List<SearchResult>.merge(): List<Movie> {
    val result = arrayListOf<Movie>()
    this.forEach { searchResult ->
        searchResult.Search!!.forEach { movie ->
            result.add(movie)
        }
    }

    return result
}

class FakeRestController @Inject constructor(private val context: Context, private val schedulerProvider: SchedulerProvider): RestController {
    private val movieTypesDataSource: List<MovieType> = listOf(
            MovieType(1, "episode"),
            MovieType(2, "game"),
            MovieType(3, "movie"),
            MovieType(4, "series")
    )

    companion object {
        @JvmStatic
        private var mSearchResults: SearchResults? = null
        @JvmStatic
        private var mDetails: MovieDetail? = null

        private fun prepareStatics(context: Context) {
            if(mSearchResults == null) {
                var inputStream = context.assets.open("batman_search_results.json")
                var buffer = ByteArray(inputStream.available())

                inputStream.read(buffer)

                mSearchResults = GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                        .fromJson(String(buffer, Charset.forName("UTF-8")), SearchResults::class.java)

                inputStream.close()

                inputStream = context.assets.open("batman_detail.json")
                buffer = ByteArray(inputStream.available())

                inputStream.read(buffer)

                mDetails = GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                        .fromJson(String(buffer, Charset.forName("UTF-8")), MovieDetail::class.java)

                inputStream.close()

            }
        }
    }

    override fun getMovieTypes(success: (List<MovieType>) -> Unit, error: (Throwable) -> Unit) {
        prepareStatics(context)
        success(movieTypesDataSource)
    }

    override fun getMovieType(code: Int, success: (MovieType) -> Unit, error: (Throwable) -> Unit) {
        prepareStatics(context)
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
        prepareStatics(context)
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
        prepareStatics(context)
        Observable
                .defer {
                    Observable.just(mSearchResults!!.results.merge().filter {
                        title.isNotBlank() &&
                                (it.Title ?: "").toLowerCase().contains(title.toLowerCase()) &&
                                (type.isNullOrBlank() || (it.Type ?:"").toLowerCase() == type) &&
                                (year.isNullOrBlank() || (it.Year ?: "") == year)
                    })
                }
                .compose(schedulerProvider.applySchedulers())
                .map {
                    val sz = it.size
                    val pgs = (sz / 10) + (if (sz % 10 > 0) 1 else 0)
                    if(page == 0 || page > pgs) {
                        return@map listOf<Movie>()
                    }

                    it.subList(10*(page-1), minOf(10*page, it.size))
                }
                .subscribe {
                    success(it)
                }
    }

    override fun getById(id: String, success: (MovieDetail) -> Unit, error: (Throwable) -> Unit) {
        prepareStatics(context)
        Observable
                .defer {
                    Observable.just(mDetails!!)
                }
                .compose(schedulerProvider.applySchedulers())
                .subscribe {
                    success(it)
                }
    }
}