package com.ch4k4uw.omdbapp.fragmentutil.abstraction

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

interface AppFragmentManager {
    fun beginTransaction(fragmentManager: FragmentManager): AppFragmentTransaction
    interface AppFragmentTransaction {
        fun animate(): AppFragmentTransaction

        fun replace(containerViewId: Int, fragment: Fragment, tag: String): AppFragmentTransaction

        fun addToBackStack(): AppFragmentTransaction

        fun commit(): Int

    }
}