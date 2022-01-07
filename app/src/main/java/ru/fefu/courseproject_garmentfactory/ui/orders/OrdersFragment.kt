package ru.fefu.courseproject_garmentfactory.ui.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.databinding.FragmentFittingsBinding

class OrdersFragment : Fragment() {
    private var _binding: FragmentFittingsBinding? = null
    private val binding get() = _binding!!
    private val orders = mutableListOf<OrderListData>()
    private val adapter = OrdersRecyclerViewAdapter(orders)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFittingsBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_navigation_orders_to_orderDetailsFragment,arguments)
        }

    }

    private fun fillDate() {
        for (i in 1..20){
            orders.add(OrderListData((1..100).random(), "CODE","CUSTOMER","STATUS"))
        }
    }
}