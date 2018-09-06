package com.ch4k4uw.omdbapp.mvp.mainfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.ch4k4uw.omdbapp.R

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