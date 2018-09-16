package com.ch4k4uw.omdbapp.mvp.mainfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.ch4k4uw.omdbapp.R

class MovieReleaseYearFragmentDialog: DialogFragment() {
    var onYearConfirmed = {_:Int?->}
    private var lastYear = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val displayMetrics = DisplayMetrics()
        val text = EditText(activity)
        text.id = R.id.release_year
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

        text.minWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, displayMetrics).toInt()
        return AlertDialog.Builder(context!!)
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