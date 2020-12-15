package fnu.bugfix.chart

import android.content.Context
import android.util.AttributeSet
import com.github.mikephil.charting.charts.BarChart

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2020-12-14 11:22 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */

class BarChartRound @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BarChart(context, attrs, defStyleAttr) {
    override fun init() {
        super.init()
        this.mRenderer = BarChartRoundRenderer(this, this.mAnimator, this.mViewPortHandler)
    }
}