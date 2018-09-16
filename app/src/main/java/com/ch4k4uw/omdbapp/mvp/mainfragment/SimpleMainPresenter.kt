package com.ch4k4uw.omdbapp.mvp.mainfragment

import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.domain.common.abstraction.application.DetailApplicationService
import com.ch4k4uw.domain.common.abstraction.application.SearchApplicationService
import com.ch4k4uw.domain.moviecatalog.abstraction.application.ListTypesApplicationService
import com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction.MainPresenter
import com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction.MainView
import com.ch4k4uw.omdbapp.rx.ComposerProvider
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SimpleMainPresenter @Inject constructor(
        private val searchSvc: SearchApplicationService<Movie>,
        private val movieTypeSvc: ListTypesApplicationService<MovieType>,
        private val detailMovieSvc: DetailApplicationService<MovieDetail>,
        composer: ComposerProvider): MainPresenter {

    private var mView: MainView? = null
    private var mMovies = arrayListOf<Movie>()
    private var mMovieTypes = arrayListOf<MovieType>()
    private var mCurrPage = 1
    private var mLastPageReached = false

    private var mSearchQuery = ""
    private var mTypeIdFilter: Long? = null
    private var mYearFilter: Int? = null
    private val mSearchSubject = BehaviorSubject.create<String>()
    private var mSearchSubscription: Disposable? = null

    init {
        mSearchSubscription = mSearchSubject
                .compose(composer.stream().applyDebounce(500).applySchedulers().apply())
                .distinctUntilChanged()
                .subscribe {
                    mSearchQuery = it
                    search()

                }
    }

    override fun updateView() {
        if(mMovies.isNotEmpty()) {
            mView?.addListItems(mMovies)
        }
        if(mMovieTypes.isNotEmpty()) {
            mView?.setMovieTypesList(mMovieTypes)
        } else {
            loadMovieTypes()
        }
    }

    override fun loadMovieTypes() {
        mView?.showProgress()
        movieTypeSvc.list({
            mView?.hideProgress()

            mMovieTypes.clear()
            mMovieTypes.addAll(it)

            mView?.setMovieTypesList(it)

        }, {
            it.printStackTrace()
            mView?.hideProgress()
        })
    }

    override fun loadNextMoviesPage() {
        if(!mLastPageReached) {
            ++mCurrPage
            search(false)
        }
    }

    override fun search(title: String) {
        mSearchSubject
                .onNext(title)
    }

    override fun search(typeId: Long?) {
        mTypeIdFilter = typeId
        search()
    }

    override fun search(year: Int?) {
        mYearFilter = year
        search()
    }

    override fun clearSearch() {
        mLastPageReached = false
        mSearchQuery = ""
        mTypeIdFilter = null
        mYearFilter = null
        mCurrPage = 1

        mView?.clearList()
        mView?.resetFiltersState()

    }

    override fun detailMovie(movie: Movie) {
        mView?.showProgress()
        detailMovieSvc.detail(movie.id2, {
            mView?.hideProgress()
            mView?.showMovieDetails(it)
        }, {
            it.printStackTrace()
            mView?.hideProgress()
        })
    }

    override var view: MainView?
        get() = mView
        set(value) {
            if(value == null) {
                mView?.hideProgress(true)
            }
            mView = value

        }

    private fun search(clearList: Boolean = true) {
        mView?.showProgress()
        if(clearList) {
            mCurrPage = 1
            mLastPageReached = false
        }
        searchSvc.search(mSearchQuery, mTypeIdFilter, mYearFilter, mCurrPage, {
            if(clearList) {
                mView?.clearList()
                mMovies.clear()
            }

            if(it.isEmpty()) {
                mLastPageReached = true
            } else {
                mMovies.addAll(it)
                mView?.addListItems(it)
            }

            mView?.hideProgress()

        }, {
            it.printStackTrace()
            mView?.hideProgress()
        })
    }

}