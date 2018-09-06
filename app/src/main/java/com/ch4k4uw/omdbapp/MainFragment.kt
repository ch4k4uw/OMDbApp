package com.ch4k4uw.omdbapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
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
import android.os.Looper
import android.text.InputType
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.inputmethod.EditorInfo


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

        if(savedInstanceState != null) {
            presenter.updateView()
        } else {
            presenter.loadMovieTypes()
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_adv_filter ->
                filterOptionsDialog.show((activity as AppCompatActivity).supportFragmentManager, "filter_options")
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
    }

    private val listItemClick = { movie: Movie ->
        presenter.detailMovie(movie)
    }

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

    class FilterOptionsFragmentDialog: DialogFragment() {
        var onFilterTypeSelected = { _:Int -> }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog
                    .Builder(activity as Context)
                    .setTitle(R.string.filter_dialog_options_title)
                    .setItems(R.array.filter_dialog_options) { dialog, which ->
                        onFilterTypeSelected(which)
                        dialog.dismiss()
                    }
                    .create()
        }

        override fun onDestroyView() {
            if(dialog != null && retainInstance) {
                dialog.setDismissMessage(null)
            }
            super.onDestroyView()
        }
    }

    class MovieReleaseYearFragmentDialog: DialogFragment() {
        var onYearConfirmed = {_:Int?->}
        private var lastYear = ""
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val displaymetrics = DisplayMetrics()
            val text = EditText(activity)
            text.hint = getString(R.string.year_dialog_hint)
            text.setText(lastYear)
            text.inputType = InputType.TYPE_CLASS_NUMBER
            text.setSelectAllOnFocus(true)

            val confirm = {
                lastYear = text.text.toString()
                if(lastYear.isNotBlank()) {
                    onYearConfirmed(lastYear.toInt())
                } else {
                    onYearConfirmed(null)
                }
            }

            text.setOnEditorActionListener { v, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    confirm()
                    dialog.dismiss()
                }

                true
            }

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            Looper.myQueue().addIdleHandler {
                imm.showSoftInput(text, 0)
                false
            }

            text.minWidth = TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 50f, displaymetrics).toInt()
            return AlertDialog
                    .Builder(context!!)
                    .setView(text)
                    .setPositiveButton(R.string.confirm_button) { dialog, _ ->
                        confirm()
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.cancel_button) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
        }

        override fun onDestroyView() {
            if(dialog != null && retainInstance) {
                dialog.setDismissMessage(null)
            }
            super.onDestroyView()
        }
    }

    class MovieTypeFragmentDialog: DialogFragment() {
        var onMovieTypeSelected = { _: Long -> }
        var dataSource: () -> List<MovieType> = { listOf() }
        var lastSelection = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true

        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val rawOptions = arrayListOf(MovieType(0L, getString(R.string.movie_type_filter_all_option)))
            rawOptions.addAll(dataSource())

            val options = rawOptions.map { it.name }.toTypedArray()

            return if(options.isNotEmpty()) {
                AlertDialog
                        .Builder(activity as Context)
                        .setTitle(R.string.movie_type_dialog_title)
                        .setSingleChoiceItems(options, lastSelection) { dialog, which ->
                            dialog.dismiss()
                            onMovieTypeSelected(rawOptions[which].id)
                            lastSelection = which
                        }
                        .create()
            } else {
                super.onCreateDialog(savedInstanceState)
            }
        }

        override fun onDestroyView() {
            if(dialog != null && retainInstance) {
                dialog.setDismissMessage(null)
            }
            super.onDestroyView()
        }
    }
}