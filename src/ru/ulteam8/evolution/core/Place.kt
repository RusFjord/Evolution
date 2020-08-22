package ru.ulteam8.evolution.core

class Place : EvoObject {

    var entity : Entity? = null
    var foodLevel : Int
        private set

    init {
        foodLevel = (Math.random() * 250).toInt()
    }

    override fun update() {

    }

}
