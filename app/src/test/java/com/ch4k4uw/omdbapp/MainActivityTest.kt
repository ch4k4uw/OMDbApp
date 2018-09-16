package com.ch4k4uw.omdbapp

import android.support.v4.app.SupportActivity
import android.view.MenuItem
import com.ch4k4uw.omdbapp.mvp.mainfragment.FilterOptionsFragmentDialog
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.fakes.RoboMenuItem
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@RunWith(RobolectricTestRunner::class)
@Config(application = AppTest::class, constants = BuildConfig::class)
class MainActivityTest {
    private val activity = Robolectric.setupActivity(MainActivity::class.java)!!

    @Test
    fun onCreate() {
        assertTrue(
                activity.
                        supportFragmentManager.
                        findFragmentByTag("main") is MainFragment
        )
    }

    @Test
    fun showAdvancedFiltersOptionsDialog() {
        val menuItem = RoboMenuItem(R.id.action_adv_filter)

        SupportFragmentTestUtil.startFragment(MainFragment(), MainActivity::class.java)

        activity.supportFragmentManager.findFragmentByTag("main").onOptionsItemSelected(menuItem)

        assertTrue(
                activity.
                        supportFragmentManager.
                        findFragmentByTag("filter_options") is FilterOptionsFragmentDialog
        )
    }
}

