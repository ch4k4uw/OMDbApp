package com.ch4k4uw.omdbapp.mvp

interface Presenter<T> where T: View {
    var view: T?

}