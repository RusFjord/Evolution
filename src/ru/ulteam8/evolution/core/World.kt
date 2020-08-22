package ru.ulteam8.evolution.core

import ru.ulteam8.evolution.graphics.Display
import java.awt.Color
import java.awt.Graphics2D

private const val NONE_FOOD_COLOR = 0xFFEAFAF1.toInt()
private const val FEW_FOOD_COLOR = 0xFF82E0AA.toInt()
private const val NORMAL_FOOD_COLOR = 0xFF28B463.toInt()
private const val LOT_FOOD_COLOR = 0xFF186A3B.toInt()


class World(val width: Int, val height: Int) : EvoObject {
    val size = width * height
    private var places = Array<Place>(size) { Place() }

    override fun update() {
        places.forEach {
            it.update()
        }
    }

    fun render() {
        val graphics2D = Display.getGraphics() as Graphics2D
        var i = 0
        while (i < size) {
            val y = (i / width).toInt()
            val x = i - width * y
            if (places[i].entity != null) {

            } else {
                val colorInt = when (places[i].foodLevel) {
                    0 -> NONE_FOOD_COLOR
                    in 1..99 -> FEW_FOOD_COLOR
                    in 100..199 -> NORMAL_FOOD_COLOR
                    else -> LOT_FOOD_COLOR
                }

                graphics2D.color = Color(colorInt)
                graphics2D.drawLine(x, y, x, y)
            }
            i++
        }
    }

}
