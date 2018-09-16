package com.ch4k4uw.omdbapp.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import com.ch4k4uw.omdbapp.R
import com.ch4k4uw.omdbapp.fragmentutil.abstraction.AppFragmentManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class AppFragment: Fragment(), com.ch4k4uw.omdbapp.mvp.View {
    @Inject
    lateinit var appFragmentManager: AppFragmentManager
    lateinit var toolbar: Toolbar

    private var progressInstances = 0
    private var progress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)

        AndroidSupportInjection.inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar) as Toolbar
        progress = view.findViewById(R.id.progress)
        val activity = activity as AppCompatActivity


        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        activity.supportActionBar?.title = getString(R.string.app_name)

    }

    override fun showProgress() {
        progress?.visibility = View.VISIBLE
        ++progressInstances
    }

    override fun hideProgress(force: Boolean) {
        if(progressInstances - 1 == 0 || force) {
            progress?.visibility = View.GONE
        }

        progressInstances = if (force) 0 else progressInstances - 1
        if(progressInstances < 0) {
            throw RuntimeException("Ops, something goes wrong!!!")
        }

    }
}