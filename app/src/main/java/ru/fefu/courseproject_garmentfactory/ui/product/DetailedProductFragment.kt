package ru.fefu.courseproject_garmentfactory.ui.product

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.databinding.FragmentDetailedProductBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PRODUCT = "product_id"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailedProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedProductFragment : Fragment() {
    private var product_id: Int = 0

//    private val changesList = listOf("Боба", "Биба", "Абоба")

    private var _binding: FragmentDetailedProductBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product_id = it.getInt(ARG_PRODUCT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailedProductBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_detailed_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        putProduct(getProduct(product_id))
    }

    fun addChanges() {

    }

    fun getProduct(id: Int) : Product{
        // TODO("get from DB")
        return Product(
            imgSrc = "https://img.gazeta.ru/files3/431/14183431/cat-pic_32ratio_900x600-900x600-36824.jpg",
            vendor = 2281337,
            length = 10,
            width = 1,
            cloths = listOf("шёлк", "хлопок"),
            accessories = listOf("пуговица резная"),
            dateOfOlden = null
            )
    }

    @SuppressLint("SetTextI18n")
    fun putProduct(product: Product) {
        view?.let {
            val img = it.findViewById<ImageView>(R.id.DetailedProductImage)
            val vendor = it.findViewById<TextView>(R.id.DetailedProductVendorText)
            val length = it.findViewById<TextView>(R.id.DetailedProductLengthText)
            val width = it.findViewById<TextView>(R.id.DetailedProductWidthText)
            val clothes = it.findViewById<TextView>(R.id.DetailedProductClothsList)
            val accessories = it.findViewById<TextView>(R.id.DetailedProductAccessoriesList)
            img.setImageResource(R.drawable.default_image)
            vendor.text = product.vendor.toString()
            length.text = "${product.length} м"
            width.text = "${product.width} м"
            var clothesText = ""
            product.cloths.forEach {clothesText += it + '\n'}
            clothes.text = clothesText
            var accessoriesText = ""
            product.accessories.forEach {accessoriesText += it + '\n'}
            accessories?.text = accessoriesText
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param prod_id Product id.
         * @return A new instance of fragment DetailedProductFragment.
         */
        @JvmStatic
        fun newInstance(prod_id: Int) =
            DetailedProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PRODUCT, prod_id)
                }
            }
    }
}