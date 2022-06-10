package com.raed.rasmview.renderer

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.raed.rasmview.util.LogUtil

internal class RectRenderer(
    private val rect: Rect,
    color: Int,
): Renderer {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        this.color = color
    }

    override fun render(canvas: Canvas) {
        LogUtil.logFlow("RectRenderer")
        canvas.drawRect(rect, paint)
    }

}