package com.ch4k4uw.omdbapp

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.EditText
import com.ch4k4uw.omdbapp.mvp.detailfragment.DetailFragment
import com.ch4k4uw.omdbapp.mvp.mainfragment.FilterOptionsFragmentDialog
import com.ch4k4uw.omdbapp.mvp.mainfragment.MovieReleaseYearFragmentDialog
import com.ch4k4uw.omdbapp.mvp.mainfragment.MovieTypeFragmentDialog
import com.ch4k4uw.omdbapp.shadows.ShadowCollapsingToolbarLayout
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.fakes.RoboMenuItem

@RunWith(RobolectricTestRunner::class)
@Config(application = AppTest::class, constants = BuildConfig::class, shadows = [ShadowCollapsingToolbarLayout::class])
class MainFragmentTest {
    private val fragment = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .visible()
            .get()
            .supportFragmentManager
            .findFragmentByTag("main") as MainFragment

    @Before
    fun setUp() {
        //SupportFragmentTestUtil.startFragment(fragment, MainActivity::class.java)
    }

    @Test
    fun onOptionsItemSelected() {
        addListItems()

        val list = fragment.view?.findViewById<RecyclerView?>(R.id.recyclerView)!!

        val menuItem = RoboMenuItem(R.id.action_adv_filter)

        fragment.onOptionsItemSelected(menuItem)

        val filterOptions = fragment.fragmentManager!!.findFragmentByTag("filter_options")

        assertTrue(
                filterOptions is FilterOptionsFragmentDialog
        )

        val filterOptionsDialog = (filterOptions as FilterOptionsFragmentDialog).dialog as AlertDialog
        val typeFilterOption = filterOptionsDialog.listView.adapter.getView(0, null, filterOptionsDialog.listView)
        val releaseYearFilterOption = filterOptionsDialog.listView.adapter.getView(1, null, filterOptionsDialog.listView)

        filterOptionsDialog.listView.performItemClick(typeFilterOption, 0, typeFilterOption.id.toLong())

        val movieTypes = fragment.fragmentManager!!.findFragmentByTag("movie_type")

        assertTrue(
                movieTypes is MovieTypeFragmentDialog
        )

        val movieTypeDialog = (movieTypes as MovieTypeFragmentDialog).dialog as AlertDialog
        val movieMovieTypeOption = movieTypeDialog.listView.adapter.getView(0, null, movieTypeDialog.listView)

        movieTypeDialog.listView.performItemClick(movieMovieTypeOption, 3, movieMovieTypeOption.id.toLong())

        filterOptionsDialog.listView.performItemClick(releaseYearFilterOption, 1, releaseYearFilterOption.id.toLong())

        assertNotEquals(0, list.adapter.itemCount)

        val releaseYear = fragment.fragmentManager!!.findFragmentByTag("release_year")

        assertTrue(
                releaseYear is MovieReleaseYearFragmentDialog
        )

        val releaseYearDialog = (releaseYear as MovieReleaseYearFragmentDialog).dialog as AlertDialog

        assertNotNull(releaseYearDialog.findViewById(R.id.release_year))
        assertTrue(releaseYearDialog.findViewById<View>(R.id.release_year) is EditText)
        assertNotNull(releaseYearDialog.getButton(DialogInterface.BUTTON_POSITIVE))
        assertNotNull(releaseYearDialog.getButton(DialogInterface.BUTTON_NEGATIVE))

        val releaseYearEditText = releaseYearDialog.findViewById<EditText>(R.id.release_year)!!

        releaseYearEditText.setText("1555")

        releaseYearDialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick()

        assertEquals(0, list.adapter.itemCount)

    }

    @Test
    fun addListItems() {
        val list = fragment.view?.findViewById<RecyclerView?>(R.id.recyclerView)
        assertNotNull(list)

        search("Batman")

        assertNotNull(list!!.adapter)
        assertNotEquals(0, list.adapter.itemCount)

    }

    @Test
    fun clearList() {
        val list = fragment.view?.findViewById<RecyclerView?>(R.id.recyclerView)
        assertNotNull(list)

        search("Batman")

        assertNotNull(list!!.adapter)

        search("")

        assertEquals(0, list.adapter.itemCount)

    }

    @Test
    fun resetFiltersState() {
        val list = fragment.view?.findViewById<RecyclerView?>(R.id.recyclerView)
        assertNotNull(list)

        addListItems()

        val searchItem = fragment.toolbar.menu.findItem(R.id.action_search)

        searchItem.collapseActionView()

        assertEquals(0, list!!.adapter.itemCount)

    }

    @Test
    fun showMovieDetails() {
        val list = fragment.view?.findViewById<RecyclerView?>(R.id.recyclerView)
        assertNotNull(list)

        addListItems()

        val item = list!!.adapter.createViewHolder(list, 0)
        list.adapter.bindViewHolder(item, 0)

        item.itemView.performClick()

        assertTrue(
                fragment.activity!!.
                        supportFragmentManager.
                        findFragmentByTag("detail_fragment") is DetailFragment
        )

    }

    private fun search(query: String) {
        val searchItem = fragment.toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView?

        assertNotNull(searchView)

        searchItem.expandActionView()
        searchView!!.setQuery(query, true)

    }
}