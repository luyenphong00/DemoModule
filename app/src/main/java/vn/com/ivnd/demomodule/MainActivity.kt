package vn.com.ivnd.demomodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatTextView>(R.id.open_image).setOnClickListener {
            requestPermissionX(this) { list ->
                ImageDialog(list) {
                    Glide.with(this)
                        .load(it.path)
                        .into(findViewById<AppCompatImageView>(R.id.ivImg))
                }.show(supportFragmentManager, "")
            }
        }

    }
}
