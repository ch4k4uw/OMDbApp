package com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction

import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.omdbapp.mvp.View

interface MainView: View {
    fun addListItems(items: List<Movie>)

    fun setMovieTypesList(movieTypes: List<MovieType>)

    fun clearList()

    fun resetFiltersState()

    fun showMovieDetails(movie: MovieDetail)

}