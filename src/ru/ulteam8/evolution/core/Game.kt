package ru.ulteam8.evolution.core

import ru.ulteam8.evolution.graphics.Display
import ru.ulteam8.evolution.utils.Time
import java.awt.Graphics2D

private const val WIDTH = 800
private const val HEIGHT = 600
private const val TITLE = "Evolution"
private const val CLEAR_COLOR : Int = 0xff000000.toInt()
private const val NUM_BUFFERS = 3

private const val UPDATE_RATE = 60.0f
private val UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE
private const val IDLE_TIME = 1L

//temp
private var x = 350
private var y = 250
private var delta = 0.0
private var radius = 100
//temp end

class Game() : Runnable {

    private var running = false
    private lateinit var gameThread: Thread
    private val graphics: Graphics2D

    init {
        Display.create(WIDTH, HEIGHT, TITLE, NUM_BUFFERS)
        graphics = Display.getGraphics() as Graphics2D
    }

    @Synchronized fun start() {
        if (running) return
        running = true
        gameThread = Thread(this)
        gameThread.start()
    }
    @Synchronized fun stop() {
        if (!running) return
        running = false
        try {
            gameThread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        cleanUp()
    }

    private fun update() {
        delta += .02
    }

    private fun render() {
        Display.clear(CLEAR_COLOR)
        graphics.fillOval(x + (Math.sin(delta) * 200).toInt(), y, radius, radius)
        Display.swapBuffers()
    }

    private fun cleanUp() {
        Display.destroy()
    }

    override fun run() {
        var fps = 0
        var upd = 0
        var updl = 0

        var count = 0L

        var delta = 0.0f

        var lastTime = Time.get()
        while (running) {
            val now = Time.get()
            val elapsedTime = now - lastTime
            lastTime = now
            count += elapsedTime
            delta += elapsedTime / UPDATE_INTERVAL
            var isRender = false
            while ( delta > 1.0f) {
                update()
                upd++
                delta--
                if (isRender) updl++
                isRender = true
            }
            if (isRender) {
                render()
                fps++
            } else {
                try {
                    Thread.sleep(IDLE_TIME)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            if (count >= Time.SECOND) {
                Display.setTitle("$TITLE || FPS: $fps | UPD: $upd | UPDL: $updl")
                fps = 0
                upd = 0
                updl = 0
                count = 0
            }
        }
    }
}