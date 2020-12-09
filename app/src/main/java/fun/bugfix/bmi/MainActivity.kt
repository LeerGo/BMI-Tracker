package `fun`.bugfix.bmi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * 1. 建立个人档案 年龄、性别、身高、体重
 * 2. 输入目标体重
 * 4. 输入当前体重
 * 5. 设置功能 - 语言、提醒、数据备份（先不做）
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ivAdd = findViewById<ImageView>(R.id.ivAdd)
        ivAdd.setOnClickListener {
            var a =     1 / 0
        }
    }
}