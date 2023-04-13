package vn.com.ivnd.demomodule

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GalleryAdapter(var list: List<ModelImage>) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    var callBack : ((ModelImage) -> Unit?)? = null
    var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        return ViewHolder(View.inflate(mContext, R.layout.row_image_sdk, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(holder.adapterPosition, list[holder.adapterPosition])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imvPhoto = view.findViewById<ImageView>(R.id.imvPhoto)
        fun bindData(position: Int, image: ModelImage) {
            mContext?.let {
                Glide.with(it)
                    .load(image.path)
                    .into(imvPhoto)
            }
            imvPhoto.setOnClickListener {
                callBack?.invoke(image)
            }

        }
    }

}
