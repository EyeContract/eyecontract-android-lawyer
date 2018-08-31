package us.buddman.eyecontractuser.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Point(var x: Float, var y: Float, var check: Boolean, var color: Int)


class SignatureView(context: Context) : View(context) {
    var points = ArrayList<Point>()
    var color = Color.BLACK
    val p = Paint()

    init {
        p.strokeWidth = 15F
        p.isAntiAlias = true
        p.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        for (i in 1 until points.size) {
            p.color = points.get(i).color
            if (!points[i].check)
                continue
            canvas.drawLine(points[i - 1].x, points[i - 1].y, points[i].x, points[i].y, p)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                points.add(Point(x, y, false, color))
                points.add(Point(x, y, true, color))
            }
            MotionEvent.ACTION_MOVE -> points.add(Point(x, y, true, color))
            MotionEvent.ACTION_UP -> {
            }
        }
        invalidate()
        return true
    }

}