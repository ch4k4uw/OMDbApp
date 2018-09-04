package com.ch4k4uw.domain.abstraction.command

/**
 *
 */
interface Command {
    /**
     *
     */
    fun exec(complete: () -> Unit)
}