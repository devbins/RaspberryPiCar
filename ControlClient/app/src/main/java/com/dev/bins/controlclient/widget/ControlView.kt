package com.dev.bins.controlclient.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * Created by bin on 13/06/2017.
 */


class ControlView : View {

    var width: Int? = 0
    var height: Int? = null

    var whitePaint: Paint
    var hightLightPiant: Paint

    var cx: Float = 0.toFloat()
    var cy: Float = 0.toFloat()
    var radius: Float = 0.toFloat()
    var center: PointF = PointF()
    var directionChange: OnDirectionChangeListener? = null


    constructor(context: Context) : super(context) {
        Log.d(ControlView::javaClass.name, "construct")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        Log.d(ControlView::javaClass.name, "construct")

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        Log.d(ControlView::javaClass.name, "construct")

    }

    init {
        Log.d(ControlView::javaClass.name, "init")
        whitePaint = Paint()
        whitePaint.isAntiAlias = true
        whitePaint.isDither = false
        whitePaint.color = Color.WHITE

        hightLightPiant = Paint()
        hightLightPiant.isDither = false
        hightLightPiant.isAntiAlias = true
        hightLightPiant.color = Color.argb(200, 200, 200, 200)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(Math.min(width, height), Math.min(width, height))
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
        cx = width!!.div(2.toFloat())
        center.x = cx
        cy = height!!.div(2.toFloat())
        center.y = cy
        radius = width!!.div(4.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        canvas!!.drawCircle(width!!.div(2.toFloat()), height!!.div(2.toFloat()), width!!.div(2.toFloat()), whitePaint)
        canvas!!.drawCircle(cx, cy, radius, hightLightPiant)
    }

    var lastX: Float = 0.toFloat()
    var lastY: Float = 0.toFloat()
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                cx = event.x
                cy = event.y
                checkEdge()
                var angle = calculateAngle(cx, cy)
                when (angle) {
                    in 0..45 -> directionChange!!.right()
                    in 45..135 -> directionChange!!.up()
                    in 135..225 -> directionChange!!.left()
                    in 225..315 -> directionChange!!.down()
                    in 315..350 -> directionChange!!.right()
                }

            }
            MotionEvent.ACTION_UP -> {
                cx = width!!.div(2.toFloat())
                cy = height!!.div(2.toFloat())
                directionChange!!.pause()
            }
        }
        invalidate()
        return true
    }

    private fun calculateAngle(cx: Float, cy: Float): Int {
        var a = cx - center.x
        var b = cy - center.y
        var len = Math.sqrt(a * a + b * b.toDouble())
        var arc = Math.acos(a / len)
        var angle = (arc * 180 / Math.PI).toInt()
        if (cy > center.y) {
            angle = 360 - angle
        }
        return angle

    }

    private fun checkEdge() {
        if (cx < radius) {
            cx = radius
        }
        if (cx > radius * 3) {
            cx = radius * 3
        }
        if (cy < radius) {
            cy = radius
        }
        if (cy > radius * 3) {
            cy = radius * 3
        }
    }

    interface OnDirectionChangeListener {
        fun left()
        fun right()
        fun up()
        fun down()
        fun pause()
    }

}