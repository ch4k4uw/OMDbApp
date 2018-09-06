package com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction

import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.omdbapp.mvp.Presenter

interface MainPresenter: Presenter<MainView> {
    fun updateView()

    fun loadMovieTypes()

    fun loadNextMoviesPage()

    fun search(title: String)

    fun search(typeId: Long?)

    fun search(year: Int?)

    fun clearSearch()

    fun detailMovie(movie: Movie)

}