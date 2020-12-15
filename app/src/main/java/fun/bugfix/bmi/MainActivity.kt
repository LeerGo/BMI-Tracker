package `fun`.bugfix.bmi

import `fun`.bugfix.bmi.widget.BmiProgress
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import fnu.bugfix.chart.BarChartRound

/**
 * 1. 建立个人档案 年龄、性别、身高、体重
 * 2. 输入目标体重
 * 4. 输入当前体重
 * 5. 设置功能 - 语言、提醒、数据备份（先不做）
 */
class MainActivity : AppCompatActivity() {
    private lateinit var chart: BarChartRound
    private lateinit var cvBmi: BmiProgress
    private lateinit var pbTarget: RoundCornerProgressBar

    protected var suppliers = arrayOf("周一", "周二", "周三", "周四", "周五", "周六", "周天")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initChart()
    }

    private fun initViews() {
        chart = findViewById(R.id.chart)
        cvBmi = findViewById(R.id.cvBmi)
        pbTarget = findViewById(R.id.pbTarget)
        cvBmi.setFlag(23)
        pbTarget.progress = 80f
    }

    private fun initChart() {
        setChart()
        setXAxis()
        setYAxisLeft()
        chart.setData(getBarData())
    }

    private fun setChart() {
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.animateY(800)

        chart.isHighlightFullBarEnabled = false
        chart.description.isEnabled = false
        chart.axisRight.isEnabled = false;
        chart.legend.isEnabled = false
        chart.xAxis.setDrawGridLines(false)
        chart.xAxis.setDrawAxisLine(false)
        chart.axisLeft.setDrawGridLines(false)
        chart.axisLeft.setDrawAxisLine(false)

        val ll = LimitLine(50f, "")
        ll.lineColor = Color.parseColor("#5E5AB0")
        ll.lineWidth = .8f
        ll.enableDashedLine(20f, 8f, 0f);  //设置虚线
        chart.axisLeft.addLimitLine(ll)
    }

    private fun setXAxis() {
        val xAxis: XAxis = chart.getXAxis()
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = suppliers.size // 设置X轴标签数量
        xAxis.position = XAxis.XAxisPosition.BOTTOM // 设置X轴标签位置，BOTTOM在底部显示，TOP在顶部显示
        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = suppliers[value.toInt()]
        })
    }

    private fun setYAxisLeft() {
        val axisLeft: YAxis = chart.getAxisLeft() // 获取左边Y轴操作类
        axisLeft.axisMinimum = 0f // 设置最小值
        axisLeft.granularity = 0f // 设置Label间隔
        axisLeft.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = "${value.toInt()} KG"
        })
    }

    private fun getBarData(): BarData {
        val barEntries: MutableList<BarEntry> = ArrayList()
        for (i in suppliers.indices) {
            barEntries.add(BarEntry(i.toFloat(), getRandom(80f, 50f)))
        }
        val barDataSet = BarDataSet(barEntries, "LAR") // 新建一组柱形图，"LAR"为本组柱形图的Label
        barDataSet.color = Color.parseColor("#C9ADF6") // 设置柱形图颜色
        barDataSet.setDrawValues(false)
        val barData = BarData()
        barData.barWidth = .30f
        barData.addDataSet(barDataSet)
        return barData
    }

    protected fun getRandom(range: Float, start: Float): Float {
        return (Math.random() * range).toFloat() + start
    }
}