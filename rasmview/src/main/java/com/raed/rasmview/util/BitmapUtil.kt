package com.raed.rasmview.util

import android.graphics.Bitmap
import android.graphics.Canvas

object BitmapUtil {

    fun mergeBitmap(back: Bitmap, front: Bitmap): Bitmap? {
        val result = Bitmap.createBitmap(back.width, back.height, back.config)
        val canvas = Canvas(result)
        val widthBack = back.width
        val widthFront = front.width
        val move = ((widthBack - widthFront) / 2).toFloat()
        canvas.drawBitmap(back, 0f, 0f, null)
        canvas.drawBitmap(front, move, move, null)
        return result
    }

}