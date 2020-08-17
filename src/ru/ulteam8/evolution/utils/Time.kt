package ru.ulteam8.evolution.utils

class Time {
    companion object {
        val SECOND : Long = 1000000000L

        fun get() : Long {
            return System.nanoTime()
        }
    }
}