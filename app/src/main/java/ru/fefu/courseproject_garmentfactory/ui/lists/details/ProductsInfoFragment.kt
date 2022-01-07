package ru.fefu.courseproject_garmentfactory.ui.lists.details

import ListViewAdapterProducts
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fefu.courseproject_garmentfactory.databinding.FragmentMaterialsInfoBinding


class ProductsInfoFragment : Fragment() {
    private var _binding: FragmentMaterialsInfoBinding? = null
    private val binding get() = _binding!!
    private val historychangeList = arrayListOf<Pair<String,String>>(Pair("test","test"),Pair("test","test"),Pair("test","test"),Pair("test","test"),Pair("test","test"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMaterialsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val inflater = layoutInflater
        val myHeader = inflater.inflate(ru.fefu.courseproject_garmentfactory.R.layout.header_products_info,binding.listview, false) as ViewGroup
        binding.listview.addHeaderView(myHeader, null, false)

        binding.listview.adapter = context?.let { ListViewAdapterProducts(it,historychangeList) }
    }
}