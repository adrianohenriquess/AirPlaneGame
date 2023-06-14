package com.game.airplane.components

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.game.airplane.GameView
import com.game.airplane.R

class Flight(screenY: Int, resources: Resources?) {
    var x: Int
    var y: Int
    var width: Int
    var height: Int
    var wingCounter = 0
    var flight1: Bitmap
    var flight2: Bitmap
    var isGoingUp = false

    init {
        flight1 = BitmapFactory.decodeResource(resources, R.drawable.fly1)
        flight2 = BitmapFactory.decodeResource(resources, R.drawable.fly2)
        width = flight1.width
        height = flight1.height
        width /= 4
        height /= 4
        width *= GameView.Companion.screenRatioX.toInt()
        height *= GameView.Companion.screenRatioY.toInt()
        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false)
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false)
        y = screenY / 2
        x = (64 * GameView.Companion.screenRatioX).toInt()
    }

    val flight: Bitmap
        get() {
            if (wingCounter == 0) {
                wingCounter++
                return flight1
            }
            wingCounter--
            return flight2
        }
}