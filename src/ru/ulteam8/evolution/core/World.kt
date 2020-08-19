package ru.ulteam8.evolution.core

class World(val width: Int, val height: Int) : EvoObject {
    val size = width * height
    private val places = emptyArray<Place>()

    init {
        var i = 0
        while(i < size) {
            places[i] = Place.create()
            i++
        }
    }

    override fun update() {
        places.forEach {
            it.update()
        }
    }

    fun render() {
        val graphics2D = Display.getGraphics()
        var i = 0
        while (i < size) {
            if (places[i].entity != null) {

            } else {

            }
        }
        places.forEach {
            if (it.entity != null) {

            } else {
                when (it.foodLevel) {
                    //in 0..99 ->
                }
            }
        }
    }

}
