package com.ch4k4uw.omdbapp.mvp.mainfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.omdbapp.R

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