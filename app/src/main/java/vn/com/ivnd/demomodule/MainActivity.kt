package vn.com.ivnd.demomodule

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import java.io.FileNotFoundException
import java.io.InputStream


open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatTextView>(R.id.open_image).setOnClickListener {
            requestPermissionX(this,resultLauncher)
        }

    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Uri? = result.data?.data
                val imageStream: InputStream? = data?.let {
                    contentResolver.openInputStream(it)
                }
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                Glide.with(this)
                    .load(selectedImage)
                    .into(findViewById<AppCompatImageView>(R.id.ivImg))
            }
        }
//    protected fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(reqCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            try {
//                val imageUri: Uri? = data.data
//                val imageStream: InputStream? = imageUri?.let { contentResolver.openInputStream(it) }
//                val selectedImage = BitmapFactory.decodeStream(imageStream)
//                Glide.with(this)
//                    .load(selectedImage)
//                    .into(findViewById<AppCompatImageView>(R.id.ivImg))
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
//            }
//        } else {
//            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
//        }
//    }
}
