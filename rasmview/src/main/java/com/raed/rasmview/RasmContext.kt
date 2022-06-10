package com.raed.rasmview

import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.Config.ARGB_8888
import com.raed.rasmview.actions.ChangeBackgroundAction
import com.raed.rasmview.actions.ClearAction
import com.raed.rasmview.brushtool.BrushToolBitmaps
import com.raed.rasmview.brushtool.BrushToolStatus
import com.raed.rasmview.brushtool.model.BrushConfig
import com.raed.rasmview.renderer.RasmRendererFactory
import com.raed.rasmview.state.RasmState
import com.raed.rasmview.util.LogUtil


class RasmContext internal constructor(private val context: Context) {

    private var nullableBrushToolBitmaps: BrushToolBitmaps? = null
        set(value) {
            require(value != null)
            field = value
        }
    internal val brushToolBitmaps get() = nullableBrushToolBitmaps!!
    var brushToolStatus = BrushToolStatus()
    val hasRasm get() = nullableBrushToolBitmaps != null
    val rasmWidth get() = brushToolBitmaps.layerBitmap.width
    val rasmHeight get() = brushToolBitmaps.layerBitmap.height
    val state = RasmState(this)
    val transformation = Matrix()
    var brushConfig = BrushConfig()
    var brushColor = 0xff2187bb.toInt()
    var rotationEnabled = false
    internal var backgroundColor = -1


    fun setRasm(
        drawingWidth: Int,
        drawingHeight: Int,
    ) {
        val backgroundBitmap = Bitmap.createBitmap(drawingWidth, drawingHeight, ARGB_8888)
        val mainBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.dfsf)

        if (mainBitmap.width > backgroundBitmap.width || mainBitmap.height > backgroundBitmap.height) {
            // TODO: convert main bitmap inside background bitmap
        }

        val moveLeft = (backgroundBitmap.width - mainBitmap.width) / 2f
        val moveTop = (backgroundBitmap.height - mainBitmap.height) / 2f

        val canvas = Canvas(backgroundBitmap)
        canvas.drawBitmap(backgroundBitmap, 0f, 0f, null)
        canvas.drawBitmap(mainBitmap, moveLeft, moveTop, null)

        setRasm(backgroundBitmap)
    }

    fun setRasm(rasm: Bitmap) {
        nullableBrushToolBitmaps = BrushToolBitmaps.createFromDrawing(rasm)
        state.reset()
    }

    fun exportRasm(): Bitmap {
        val rasm = Bitmap.createBitmap(rasmWidth, rasmHeight, ARGB_8888)
        val rasmRenderer = RasmRendererFactory().createOffscreenRenderer(this)
        rasmRenderer.render(Canvas(rasm))
        return rasm
    }

    fun clear() {
        state.update(
            ClearAction(),
        )
    }

    fun setBackgroundColor(color: Int) {
        state.update(
            ChangeBackgroundAction(color),
        )
    }

    internal fun resetTransformation(containerWidth: Int, containerHeight: Int) {
        LogUtil.logFlow("resetTransformation rasmWidth: ${rasmWidth.toFloat()}, rasmHeight: ${rasmHeight.toFloat()}")
        LogUtil.logFlow("resetTransformation rasmWidth: ${containerWidth.toFloat()}, rasmHeight: ${containerHeight.toFloat()}")
        transformation.setRectToRect(
            RectF(0F, 0F, rasmWidth.toFloat(), rasmHeight.toFloat()),
            RectF(0f, 0f, containerWidth.toFloat(), containerHeight.toFloat()),
            Matrix.ScaleToFit.CENTER,
        )
    }

}
