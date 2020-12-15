package `fun`.bugfix.bmi.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2020-12-14 4:15 PM
 *
 * <p>
 * lv1  < 18     8
 * lv2  [18, 24) 6
 * lv3  [24 ~ 28) 4
 * lv4  >= 28.0
 * </p>
 */
class BmiProgress @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val TAG = "CusView"

    // 设置画笔变量
    private var hPaint: Paint = Paint()
    private var nPaint: Paint = Paint()
    private val hColor = arrayListOf(
        Color.parseColor("#93BAF0"), Color.parseColor("#86D284"),
        Color.parseColor("#CDB95E"), Color.parseColor("#E68B7C"),
    )
    private val nColor = arrayListOf(
        Color.parseColor("#7B9ABE"), Color.parseColor("#72B470"),
        Color.parseColor("#CDB95E"), Color.parseColor("#B46D62"),
    )

    // 视图参数
    private lateinit var rectf: RectF
    private var space: Float = 0f
    private val rectWidth = 8f
    private val rectCount1 = 17
    private val rectCount2 = 6
    private val rectCount3 = 4
    private val rectCount4 = 21
    private val rectCount = rectCount1 + rectCount2 + rectCount3 + rectCount4

    // 高亮边界
    private var flag = -1
    private var count = -1

    init {
        hPaint.setStrokeWidth(5f);
        hPaint.setStyle(Paint.Style.FILL);
        hPaint.isAntiAlias = true
        nPaint.setStrokeWidth(5f);
        nPaint.setStyle(Paint.Style.FILL);
        nPaint.isAntiAlias = true
    }

    fun setFlag(flag: Int) {
        this.flag = flag
        this.count = -1
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        space = (width - rectWidth * rectCount) / rectCount.toFloat()

        drawBaseView(canvas)
        drawHighView(canvas)
    }

    private fun drawHighView(canvas: Canvas) {
        if (count < flag) {
            drawProgress(canvas, count)
            count += 1
            postInvalidateDelayed(40)
        } else {
            drawProgress(canvas, flag)
        }
    }

    private fun drawProgress(canvas: Canvas, count: Int) {
        for (i in 0..count) {
            rectf = RectF((space + rectWidth) * i, 0f, (space + rectWidth) * i + rectWidth, height.toFloat())

            when {
                i < rectCount1 -> hPaint.setColor(hColor[0])
                i < rectCount1 + rectCount2 -> hPaint.setColor(hColor[1])
                i < rectCount1 + rectCount2 + rectCount3 -> hPaint.setColor(hColor[2])
                i < rectCount + 1 -> hPaint.setColor(hColor[3])
            }
            canvas.drawRoundRect(rectf, rectWidth, rectWidth, hPaint)
        }
    }

    private fun drawBaseView(canvas: Canvas) {
        for (i in 0..rectCount) {
            rectf = RectF((space + rectWidth) * i, 0f, (space + rectWidth) * i + rectWidth, height.toFloat())

            when {
                i < rectCount1 -> nPaint.setColor(nColor[0])
                i < rectCount1 + rectCount2 -> nPaint.setColor(nColor[1])
                i < rectCount1 + rectCount2 + rectCount3 -> nPaint.setColor(nColor[2])
                i < rectCount + 1 -> nPaint.setColor(nColor[3])
            }
            canvas.drawRoundRect(rectf, rectWidth, rectWidth, nPaint)
        }
    }
}