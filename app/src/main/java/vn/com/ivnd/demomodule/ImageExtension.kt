package vn.com.ivnd.demomodule

import android.Manifest
import android.provider.MediaStore
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import java.io.File

fun getAllImage(activity: FragmentActivity, onLoadSuccess : (MutableList<ModelImage>) -> Unit) {
    val list = ArrayList<ModelImage>()
    val columns = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DATA,
        MediaStore.Files.FileColumns.DATE_ADDED,
        MediaStore.Files.FileColumns.MEDIA_TYPE,
        MediaStore.Video.VideoColumns.DURATION

    )
    val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)
    val orderBy = MediaStore.Files.FileColumns.DATE_ADDED
    val queryUri = MediaStore.Files.getContentUri("external")
    val mediaCursor = activity.managedQuery(
        queryUri,
        columns,
        selection,
        null,
        "$orderBy DESC"
    )

    while (mediaCursor.moveToNext()) {
        val dataColumnIndex = mediaCursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
        val type = mediaCursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE)
        val t = mediaCursor.getInt(type)
        val path = mediaCursor.getString(dataColumnIndex)
        when (t) {
            1 -> {
                if (path != "" && path != null) {
                    val media = ModelImage(path, File(path).name)
                    list.add(media)
                }
            }
        }
    }
    onLoadSuccess.invoke(list)
}

fun requestPermissionX(fragmentActivity: FragmentActivity, onLoadSuccess : (MutableList<ModelImage>) -> Unit){
    PermissionX.init(fragmentActivity)
        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        .request { allGranted, _, _ ->
            if (allGranted) {
                getAllImage(fragmentActivity) {
                    onLoadSuccess.invoke(it)
                }
            }
        }
}