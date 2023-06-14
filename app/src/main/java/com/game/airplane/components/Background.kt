package com.game.airplane.components

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.game.airplane.R

class Background(screenX: Int, screenY: Int, resources: Resources?) {
    var x = 0
    var y = 0
    var background: Bitmap

    init {
        background = BitmapFactory.decodeResource(resources, R.drawable.back)
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false)
    }
}