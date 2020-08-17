package ru.ulteam8.evolution.graphics

import java.awt.*
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import java.awt.image.DataBuffer
import javax.swing.JFrame

class Display {
    companion object {
        private var created = false
        private lateinit var window: JFrame
        private val content = Canvas()

        private lateinit var buffer: BufferedImage
        private lateinit var bufferData : DataBuffer
        private lateinit var bufferGraphics : Graphics
        private lateinit var bufferStrategy: BufferStrategy
        private var clearColor: Int = Color.GREEN.rgb
        private var delta = 0.0

        fun create(width: Int, height: Int, title: String, numBuffers: Int) {
            if (created) return
            content.setSize(width, height)
            content.background = Color.BLACK

            window = JFrame(title)
            window.isResizable = false
            window.contentPane.add(content)
            window.pack()

            window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            window.setLocationRelativeTo(null)
            window.isVisible = true
            created = true

            buffer = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
            bufferData = buffer.raster.dataBuffer
            bufferGraphics = buffer.graphics
            (bufferGraphics as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            content.createBufferStrategy(numBuffers)
            bufferStrategy = content.bufferStrategy
        }

        fun clear() {
            var i = 0
            while (i < bufferData.size) {
                bufferData.setElem(i, clearColor)
                i++
            }
        }

        fun swapBuffers() {
            val g = bufferStrategy.drawGraphics
            g.drawImage(buffer, 0, 0, null)
            bufferStrategy.show()
        }

        fun destroy() {
            window.dispose()
        }

    }
}
