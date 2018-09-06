package com.ch4k4uw.omdbapp.mvp.detailfragment

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.crosscutting.scope.FragmentScoped
import com.ch4k4uw.omdbapp.R
import com.ch4k4uw.omdbapp.mvp.AppFragment
import com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction.DetailPresenter
import com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction.DetailView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import javax.inject.Inject
import android.content.Intent
import android.support.design.widget.FloatingActionButton


@FragmentScoped
class DetailFragment: AppFragment(), DetailView {

    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this
        presenter.movieDetail = arguments?.getSerializable("details") as MovieDetail

        (view.findViewById(R.id.whole_image) as FloatingActionButton?)
                ?.setOnClickListener {
                    presenter.showImage()
                }

    }

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                (activity as AppCompatActivity)
                        .supportFragmentManager
                        .popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        presenter.view = null
    }

    override fun showImage(url: String) {
        val image = view?.findViewById(R.id.image) as SimpleDraweeView?

        val frescoListener = object: BaseControllerListener<Any>() {
            override fun onFailure(id: String?, throwable: Throwable?) {
                super.onFailure(id, throwable)
                image?.visibility = View.GONE
            }
        }

        val uri = Uri.parse(url)
        val dc = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(frescoListener)
                .setOldController(image?.controller)
                .build()

        image?.controller = dc

    }

    override fun showWholeImage(url: String) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        } catch (e: Exception) {
            //For sure was the Uri.parse() the culprit for it...
            e.printStackTrace()
        }
    }

    override fun showTitle(title: String) {
        (view?.findViewById(R.id.title) as TextView?)
                ?.text = title

        (activity as AppCompatActivity?)
                ?.supportActionBar?.title = title
    }

    override fun showRating1(rating: String) {
        (view?.findViewById(R.id.rating1) as TextView?)
                ?.text = rating
    }

    override fun showRating2(rating: String) {
        (view?.findViewById(R.id.rating2) as TextView?)
                ?.text = rating
    }

    override fun showVotes(votes: String) {
        (view?.findViewById(R.id.imdbVotes) as TextView?)
                ?.text = votes
    }

    override fun showYear(year: String) {
        (view?.findViewById(R.id.year) as TextView?)
                ?.text = year
    }

    override fun showRated(rated: String) {
        (view?.findViewById(R.id.rated) as TextView?)
                ?.text = rated
    }

    override fun showRuntime(runtime: String) {
        (view?.findViewById(R.id.runtime) as TextView?)
                ?.text = runtime
    }

    override fun showDate(date: String) {
        (view?.findViewById(R.id.date) as TextView?)
                ?.text = date
    }

    override fun showType(type: String) {
        (view?.findViewById(R.id.type) as TextView?)
                ?.text = type
    }

    override fun showGenre(genre: String) {
        (view?.findViewById(R.id.genre) as TextView?)
                ?.text = genre
    }

    override fun showPlot(plot: String) {
        (view?.findViewById(R.id.plot) as TextView?)
                ?.text = plot
    }

    override fun showDirector(director: String) {
        (view?.findViewById(R.id.director) as TextView?)
                ?.text = director
    }

    override fun showWriters(writers: String) {
        (view?.findViewById(R.id.writers) as TextView?)
                ?.text = writers
    }

    override fun showStarts(stars: String) {
        (view?.findViewById(R.id.stars) as TextView?)
                ?.text = stars
    }

    override fun showAwars(awards: String) {
        (view?.findViewById(R.id.awards) as TextView?)
                ?.text = awards
    }
}