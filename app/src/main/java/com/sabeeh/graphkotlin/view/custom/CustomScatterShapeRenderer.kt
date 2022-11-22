package com.sabeeh.graphkotlin.view.custom

import android.graphics.Canvas
import android.graphics.Paint
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class CustomScatterShapeRenderer : IShapeRenderer {
    override fun renderShape(
        c: Canvas?,
        dataSet: IScatterDataSet?,
        viewPortHandler: ViewPortHandler?,
        posX: Float,
        posY: Float,
        renderPaint: Paint?
    ) {
        var shapeHalf: Float? = dataSet?.getScatterShapeSize()?.div(2f);

        c!!.drawLine(
            posX - shapeHalf!!,
            posY - shapeHalf,
            posX + shapeHalf,
            posY + shapeHalf,
            renderPaint!!
        );
    }
}