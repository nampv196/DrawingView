package com.raed.rasmview.renderer

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.raed.rasmview.util.LogUtil

internal class BitmapRenderer(
    private val bitmap: Bitmap,
): Renderer {

    override fun render(canvas: Canvas) {
        LogUtil.logFlow("BitmapRenderer width: ${bitmap.width}, height: ${bitmap.height}")
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

}