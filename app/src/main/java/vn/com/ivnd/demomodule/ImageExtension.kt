package vn.com.ivnd.demomodule

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.permissionx.guolindev.PermissionX
import java.io.InputStream
fun requestPermissionX(fragmentActivity: FragmentActivity, activityResultLauncher: ActivityResultLauncher<Intent>
) {
    PermissionX.init(fragmentActivity)
        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        .request { allGranted, _, _ ->
            if (allGranted) {
                openIntentImage(activityResultLauncher)
            }
        }
}
fun openIntentImage(activityResultLauncher: ActivityResultLauncher<Intent>) {
    val photoPickerIntent = Intent(Intent.ACTION_PICK)
    photoPickerIntent.type = "image/*"
    activityResultLauncher.launch(photoPickerIntent)
}