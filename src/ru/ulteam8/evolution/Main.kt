package ru.ulteam8.evolution

import ru.ulteam8.evolution.graphics.Display
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Timer


fun main() {
    Display.create(800, 600, 3)

    val timer = Timer(1000/60, MyAction())
    timer.isRepeats = true
    timer.start()
}

private class MyAction() : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        Display.clear()
        Display.render()
        Display.swapBuffers()
    }

}
