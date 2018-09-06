package com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction

import com.ch4k4uw.omdbapp.mvp.View

interface DetailView: View {
    fun showImage(url: String)//image

    fun showWholeImage(url: String)//

    fun showTitle(title: String)//title

    fun showRating1(rating: String)//rating1

    fun showRating2(rating: String)//rating2

    fun showVotes(votes: String)//imdbVotes

    fun showYear(year: String)//year

    fun showRated(rated: String)//rated

    fun showRuntime(runtime: String)//runtime

    fun showDate(date: String)//date

    fun showType(type: String)//type

    fun showGenre(genre: String)//genre

    fun showPlot(plot: String)//plot

    fun showDirector(director: String)//director

    fun showWriters(writers: String)//writers

    fun showStarts(stars: String)//stars

    fun showAwars(awards: String)//awards
}