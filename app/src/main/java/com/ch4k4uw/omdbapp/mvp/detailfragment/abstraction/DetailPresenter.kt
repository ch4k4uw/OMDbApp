package com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction

import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.omdbapp.mvp.Presenter

interface DetailPresenter: Presenter<DetailView> {
    var movieDetail: MovieDetail

    fun showImage()

}