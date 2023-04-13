package vn.com.ivnd.demomodule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import vn.com.ivnd.demomodule.databinding.DialogImagesBinding

data class ModelImage(var path: String, var name: String)

class ImageDialog(var list: List<ModelImage>, var onSelect : (ModelImage) -> Unit) : DialogFragment() {
    private var _binding: DialogImagesBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    var adapters: GalleryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.CustomDialog
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivBack.setOnClickListener {
                dismiss()
            }
            adapters = GalleryAdapter(list)
            rcyImage.adapter = adapters
        }

        adapters?.callBack = {
            handlerEvenChoose(it)
        }
    }

    private fun handlerEvenChoose(modelImage: ModelImage){
        onSelect.invoke(modelImage)
        dismiss()
    }

}
