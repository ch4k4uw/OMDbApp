package com.ch4k4uw.omdbapp.mvp.detailfragment

import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction.DetailPresenter
import com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction.DetailView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SimpleDetailPresenter @Inject constructor(): DetailPresenter {
    private var mView: DetailView? = null
    private var mMovieDetail: MovieDetail? = null
    private val mDateFormat: DateFormat = SimpleDateFormat("DD MMMM yyyy", Locale.US)

    override var movieDetail: MovieDetail
        get() = mMovieDetail ?: MovieDetail()
        set(value) {
            mMovieDetail = value
            val ratings =
                    if (mMovieDetail!!.ratings.isNotEmpty() && mMovieDetail!!.ratings[0].value.isNotBlank())
                        mMovieDetail!!.ratings[0].value.split("/")
                    else
                        listOf()

            val rating1 = if (ratings.isNotEmpty()) ratings[0] else ""
            val rating2 = "/${if (ratings.size > 1) ratings[1] else ""}"

            mView?.showImage(mMovieDetail!!.posterUri)
            mView?.showTitle(mMovieDetail!!.title)
            mView?.showRating1(rating1)
            mView?.showRating2(rating2)
            mView?.showVotes(mMovieDetail!!.imdbVotes.toString())
            mView?.showYear("(${mMovieDetail!!.year})")
            mView?.showRated(mMovieDetail!!.rated)
            mView?.showRuntime(mMovieDetail!!.runtime)
            mView?.showDate(mDateFormat.format(mMovieDetail!!.released.time))
            mView?.showType(mMovieDetail!!.type.name)
            mView?.showGenre(mMovieDetail!!.genre)
            mView?.showPlot(mMovieDetail!!.plot)
            mView?.showDirector(mMovieDetail!!.genre)
            mView?.showWriters(mMovieDetail!!.writer)
            mView?.showStarts(mMovieDetail!!.actors.joinToString {actor ->
                actor.name
            })
            mView?.showAwars(mMovieDetail!!.awards)
        }

    override fun showImage() {
        mView?.showWholeImage(mMovieDetail?.posterUri ?: "")
    }

    override var view: DetailView?
        get() = mView
        set(value) {
            mView = value
        }
}