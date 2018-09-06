package com.ch4k4uw.omdbapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import android.widget.Toast
import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.crosscutting.scope.FragmentScoped
import com.ch4k4uw.omdbapp.mvp.AppFragment
import com.ch4k4uw.omdbapp.adapter.MovieCatalogAdapter
import com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction.MainPresenter
import com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction.MainView
import javax.inject.Inject
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import com.ch4k4uw.omdbapp.mvp.detailfragment.DetailFragment
import com.ch4k4uw.omdbapp.mvp.mainfragment.FilterOptionsFragmentDialog
import com.ch4k4uw.omdbapp.mvp.mainfragment.MovieReleaseYearFragmentDialog
import com.ch4k4uw.omdbapp.mvp.mainfragment.MovieTypeFragmentDialog


@FragmentScoped
class MainFragment: AppFragment(), MainView {
    private lateinit var list: RecyclerView
    private lateinit var nothingToShowMessage: View
    private val movieTypesDialog = MovieTypeFragmentDialog()
    private val movieReleaseYearDialog = MovieReleaseYearFragmentDialog()
    private val filterOptionsDialog = FilterOptionsFragmentDialog()
    private val lastListCount = arrayListOf(-1)

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filterOptionsDialog.onFilterTypeSelected = {
            when(it) {
                0 -> movieTypesDialog.show((activity as AppCompatActivity).supportFragmentManager, "movie_type")
                1 -> movieReleaseYearDialog.show((activity as AppCompatActivity).supportFragmentManager, "release_year")
            }
        }

        movieReleaseYearDialog.onYearConfirmed = {year ->
            lastListCount[0] = -1
            presenter.search(year)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.ch4k4uw.omdbapp.R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this

        list = view.findViewById(R.id.recyclerView)
        nothingToShowMessage = view.findViewById(R.id.nothing_to_show_message)

        configRecyclerView()

        presenter.updateView()


    }

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
        configActionBar(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_adv_filter ->
                filterOptionsDialog.show((activity as AppCompatActivity).supportFragmentManager, "filter_options")
            android.R.id.home -> activity?.finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        presenter.view = null
    }

    override fun addListItems(items: List<Movie>) {
        nothingToShowMessage.visibility = View.GONE
        list.visibility = View.VISIBLE

        val adapter = list.adapter as MovieCatalogAdapter?
        if(adapter != null) {
            items.forEach {
                adapter.addItem(it)
            }
        } else {
            list.adapter = MovieCatalogAdapter(items, listItemClick)
        }
    }

    override fun setMovieTypesList(movieTypes: List<MovieType>) {
        movieTypesDialog.dataSource = { movieTypes }
        movieTypesDialog.onMovieTypeSelected = {
            lastListCount[0] = -1
            presenter.search(if (it == 0L) null else it)
        }
    }

    override fun clearList() {
        (list.adapter as MovieCatalogAdapter?)
                ?.clear()

        list.visibility = View.GONE
        nothingToShowMessage.visibility = View.VISIBLE

    }

    override fun resetFiltersState() {
        movieTypesDialog.lastSelection = -1
    }

    override fun showMovieDetails(movie: MovieDetail) {
        Toast.makeText(activity, movie.title, Toast.LENGTH_SHORT)
                .show()
        val fragment = DetailFragment()
        val args = Bundle()
        args.putSerializable("details", movie)

        fragment.arguments = args

        fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                )
                ?.replace(R.id.fragment_container, fragment, "detail_fragment")
                ?.addToBackStack(null)
                ?.commit()

    }

    /**
     *
     */
    private val listItemClick = { movie: Movie ->
        presenter.detailMovie(movie)
    }

    /**
     *
     */
    private fun configRecyclerView() {
        val lm = StaggeredGridLayoutManager(resources.getInteger(R.integer.list_columns_count), StaggeredGridLayoutManager.VERTICAL)
        lm.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        list.setHasFixedSize(true)
        list.layoutManager = lm
        list.adapter = null

        list.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val positions = lm.findLastVisibleItemPositions(null)

                    var max = -1
                    positions.forEach {
                        max = if (it > max) it else max
                    }

                    val count = (list.adapter.itemCount * .6).toInt()
                    if (max > count) {
                        if(lastListCount[0] != list.adapter.itemCount) {
                            presenter.loadNextMoviesPage()
                            lastListCount[0] = list.adapter.itemCount

                        }
                    }
                }
            }
        })

    }

    /**
     *
     */
    private fun configActionBar(menu: Menu?) {
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView?

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.search(newText ?: "")
                return true
            }

        })
        searchItem?.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.clearSearch()
                return true
            }

        })
        searchView?.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                inputMethodManager?.hideSoftInputFromWindow(v?.windowToken, 0)
            }
        }
    }

}