package com.ch4k4uw.omdbapp.customcontrol

import android.os.Parcel
import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseBooleanArray
import android.os.Parcelable
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View


class StatefulToolbar : Toolbar {
    private var pendingState: Parcelable? = null
    private var pendingStates: SparseArray<Parcelable>? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSaveInstanceState(): Parcelable {
        val ss = super.onSaveInstanceState()

        val result = SavedState()
        val menu = menu

        result.menuSize = menu.size()
        result.title = title?.toString()
        result.subTitle = subtitle?.toString()
        if (result.menuSize > 0) {
            val count = result.menuSize
            for (i in 0 until count) {
                val menuItem = menu.getItem(i)
                result.menuItemsVisibility.put(menuItem.itemId, menuItem.isVisible)
                if (menuItem.isCheckable) {
                    result.menuItemsCheckState.put(menuItem.itemId, menuItem.isChecked)
                }
            }
        }

        result.ss = ss

        return result
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        super.dispatchRestoreInstanceState(container)
        if (pendingState != null) {
            pendingStates = container
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        if (state.title != null) {
            title = state.title
        }
        if (state.subTitle != null) {
            subtitle = state.subTitle
        }
        if (state.menuSize != menu.size()) {
            pendingState = state
            nextRestoreCall()
        }

        super.onRestoreInstanceState(state.ss)
    }

    /**
     * So fucking heavy workaround
     */
    private fun nextRestoreCall() {
        if (pendingState != null) {
            post {
                val savedState = pendingState as SavedState
                if (menu.size() != savedState.menuSize) {
                    nextRestoreCall()
                } else {
                    super.onRestoreInstanceState(savedState.ss)
                    pendingState = null

                    if (pendingStates != null) {
                        for (i in 0 until pendingStates!!.size()) {
                            val id = pendingStates!!.keyAt(i)
                            if (id != getId()) {
                                findViewById<View?>(id)
                                        ?.restoreHierarchyState(pendingStates)
                            }
                        }
                        pendingStates = null
                    }

                    clearFocus()

                    val visibilityStates = savedState.menuItemsVisibility.size()
                    if (visibilityStates > 0) {
                        for (i in 0 until visibilityStates) {
                            val id = savedState.menuItemsVisibility.keyAt(i)
                            val visibility = savedState.menuItemsVisibility.valueAt(i)

                            val menuItem = menu.findItem(id)
                            if (menuItem != null) {
                                menuItem.isVisible = visibility
                            }
                        }
                    }

                    val checkStates = savedState.menuItemsCheckState.size()
                    if (checkStates > 0) {
                        for (i in 0 until checkStates) {
                            val id = savedState.menuItemsCheckState.keyAt(i)
                            val checked = savedState.menuItemsCheckState.valueAt(i)

                            val menuItem = menu.findItem(id)
                            if (menuItem != null) {
                                menuItem.isCheckable = true
                                menuItem.isChecked = checked
                            }
                        }
                    }
                }
            }
        }
    }

    private class SavedState : Parcelable {
        internal var menuSize: Int = 0
        internal var title: String? = null
        internal var subTitle: String? = null
        internal var menuItemsVisibility: SparseBooleanArray
        internal var menuItemsCheckState: SparseBooleanArray
        internal lateinit var ss: Parcelable

        internal constructor() {
            menuItemsVisibility = SparseBooleanArray()
            menuItemsCheckState = SparseBooleanArray()
        }

        @SuppressLint("ParcelClassLoader")
        private constructor(source: Parcel) {
            menuSize = source.readInt()
            title = source.readString()
            subTitle = source.readString()
            menuItemsVisibility = source.readSparseBooleanArray()
            menuItemsCheckState = source.readSparseBooleanArray()
            ss = source.readBundle()
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            out.writeInt(menuSize)
            out.writeString(title)
            out.writeString(subTitle)
            out.writeSparseBooleanArray(menuItemsVisibility)
            out.writeSparseBooleanArray(menuItemsCheckState)
            out.writeParcelable(ss, 0)
        }

        companion object {

            @Suppress("unused")
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<StatefulToolbar.SavedState> {
                override fun createFromParcel(`in`: Parcel): StatefulToolbar.SavedState {
                    return StatefulToolbar.SavedState(`in`)
                }

                override fun newArray(size: Int): Array<StatefulToolbar.SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}