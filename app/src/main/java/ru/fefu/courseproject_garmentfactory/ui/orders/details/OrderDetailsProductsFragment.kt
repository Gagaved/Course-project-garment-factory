package ru.fefu.courseproject_garmentfactory.ui.lists.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderDetailsProductsBinding
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
import ru.fefu.courseproject_garmentfactory.ui.ListRecyclerViewAdapter

class OrderDetailsProductsFragment : Fragment() {
    private var _binding: FragmentOrderDetailsProductsBinding? = null
    private val binding get() = _binding!!
    private val items = mutableListOf<ItemListData>()
    private val adapter = ListRecyclerViewAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailsProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillDate()
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {
            /*val bundle = Bundle()
            bundle.putInt("ActivityID",activities[it].id )
            arguments = bundle*/
            findNavController().navigate(R.id.action_orderDetailsFragment_to_productsInfoFragment,arguments)
        }
    }

    private fun fillDate() {
        for (i in 1..20){
            items.add(ItemListData((1..100).random(), "SOMETEXT",10))
        }
    }
}