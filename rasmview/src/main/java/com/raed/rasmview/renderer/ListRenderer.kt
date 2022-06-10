package com.raed.rasmview.renderer

import android.graphics.Canvas
import com.raed.rasmview.util.LogUtil

internal class ListRenderer(
    private var renderers: List<Renderer>,
): Renderer {

    constructor(vararg renderers: Renderer): this(renderers.toList())

    override fun render(canvas: Canvas) {
        LogUtil.logFlow("ListRenderer")
        for (renderer in renderers) {
            renderer.render(canvas)
        }
    }

}
