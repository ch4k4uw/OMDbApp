package com.ch4k4uw.omdbapp.mvp.detailfragment

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import com.ch4k4uw.omdbapp.*
import com.ch4k4uw.omdbapp.shadows.ShadowCollapsingToolbarLayout
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = AppTest::class, constants = BuildConfig::class, shadows = [ShadowCollapsingToolbarLayout::class])
class DetailFragmentTest {
    private lateinit var fragment: DetailFragment

    @Before
    fun setUp() {
        val mainFragment = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .start()
                .resume()
                .visible()
                .get()
                .supportFragmentManager
                .findFragmentByTag("main") as MainFragment

        val list = mainFragment.view?.findViewById<RecyclerView?>(R.id.recyclerView)
        assertNotNull(list)

        search(mainFragment, "Batman")

        assertNotNull(list!!.adapter)
        assertNotEquals(0, list.adapter.itemCount)

        val item = list.adapter.createViewHolder(list, 0)
        list.adapter.bindViewHolder(item, 0)

        item.itemView.performClick()

        assertTrue(
                mainFragment
                        .fragmentManager!!
                        .findFragmentByTag("detail_fragment") is DetailFragment
        )

        fragment = mainFragment
                .fragmentManager!!
                .findFragmentByTag("detail_fragment") as DetailFragment

    }

    @Test
    fun showWholeImage() {
        val shadowActivity = Shadows.shadowOf(fragment.activity)

        assertNotNull(fragment.view)
        assertNotNull(fragment.view!!.findViewById<View?>(R.id.whole_image))

        fragment.view!!.findViewById<View>(R.id.whole_image).performClick()

        val nextActivity = shadowActivity.nextStartedActivity
        assertEquals(Intent.ACTION_VIEW, nextActivity.action)
        assertNotNull(nextActivity.data)

    }

    private fun search(fragment: MainFragment, query: String) {
        val searchItem = fragment.toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView?

        assertNotNull(searchView)

        searchItem.expandActionView()
        searchView!!.setQuery(query, true)

    }
}