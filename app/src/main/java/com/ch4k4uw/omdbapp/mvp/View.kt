package com.ch4k4uw.omdbapp.mvp

interface View {
    fun showProgress()

    fun hideProgress(force: Boolean = false)

}