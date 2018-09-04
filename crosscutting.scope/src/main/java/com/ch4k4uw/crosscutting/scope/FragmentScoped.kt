package com.ch4k4uw.crosscutting.scope

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class FragmentScoped