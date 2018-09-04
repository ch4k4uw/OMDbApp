package com.ch4k4uw.domain.abstraction.log

interface Log {
    /**
     *
     */
    fun debug(tag: String, message: String)

    /**
     *
     */
    fun debug(tag: String, error: Throwable)

    /**
     *
     */
    fun info(tag: String, message: String)

    /**
     *
     */
    fun error(tag: String, message: String)

    /**
     *
     */
    fun error(tag: String, message: Throwable)

}