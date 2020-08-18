package ru.ulteam8.evolution.core

class World(val width: Int, val height: Int) {
    val size = width * height
    private val places = emptyArray<Place>()

    init {
        var i = 0
        while(i < size) {
            places[i] = Place.create()
            i++
        }
    }
}
