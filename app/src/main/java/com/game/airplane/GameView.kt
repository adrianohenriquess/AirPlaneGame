package com.game.airplane

import android.content.Context
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceView
import com.game.airplane.components.Background
import com.game.airplane.components.Flight

class GameView(context: Context?, private val screenX: Int, private val screenY: Int) :
    SurfaceView(context), Runnable {
    private var thread: Thread? = null
    private var isPlaying = false
    private val paint: Paint
    private val flight: Flight
    private val background1: Background
    private val background2: Background

    init {
        screenRatioX = 1920f / screenX
        screenRatioY = 1080f / screenY
        background1 = Background(screenX, screenY, resources)
        background2 = Background(screenX, screenY, resources)
        flight = Flight(screenY, resources)
        background2.x = screenX
        paint = Paint()
    }

    override fun run() {
        while (isPlaying) {
            update()
            draw()
            sleep()
        }
    }

    private fun update() {
        background1.x -= 11
        background2.x -= 11
        if (background1.x + background1.background.width < 0) {
            background1.x = screenX
        }
        if (background2.x + background2.background.width < 0) {
            background2.x = screenX
        }

        //nao deixa passar do limite de cima da tela
        if (flight.y < 0) {
            flight.y = 0
        }

        //não deixa passar do limite de baixo da tela
        if (flight.y >= screenY - flight.height) {
            flight.y = screenY - flight.height
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawBitmap(
                background1.background,
                background1.x.toFloat(),
                background1.y.toFloat(),
                paint
            )
            canvas.drawBitmap(
                background2.background,
                background2.x.toFloat(),
                background2.y.toFloat(),
                paint
            )
            canvas.drawBitmap(flight.flight, flight.x.toFloat(), flight.y.toFloat(), paint)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun sleep() {
        try {
            Thread.sleep(17)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    fun resume() {
        isPlaying = true
        thread = Thread(this)
        thread!!.start()
    }

    fun pause() {
        try {
            isPlaying = false
            thread!!.join()
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.x < 400) {
            flight.y = event.y.toInt()
            flight.y -= 60
            if (flight.y >= screenY - flight.height) {
                flight.y = screenY - flight.height
            }
            if (flight.y < 0) {
                flight.y = 0
            }
        }
        return true
    }

    companion object {
        var screenRatioX: Float = 0.0f
        var screenRatioY: Float = 0.0f
    }
}