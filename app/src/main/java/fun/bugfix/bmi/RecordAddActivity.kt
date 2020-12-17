package `fun`.bugfix.bmi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2020-12-17 10:41 AM
 *
 * <p>
 * 添加记录页面
 * </p>
 */
class RecordAddActivity : AppCompatActivity() {
    private lateinit var tvKG: TextView;
    private lateinit var tvLB: TextView;
    private var isKG = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_activity)

        initViews()
        setViews()
    }

    private fun initViews() {
        tvKG = findViewById(R.id.tvKG)
        tvLB = findViewById(R.id.tvLB)
    }

    private fun setViews() {
        tvKG.isSelected = isKG
        tvKG.setOnClickListener {
            isKG = true
            setState()
        }
        tvLB.setOnClickListener {
            isKG = false
            setState()
        }
    }

    private fun setState() {
        tvKG.isSelected = isKG
        tvLB.isSelected = !isKG
    }
}