package ru.fefu.courseproject_garmentfactory.ui.orders

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Cloth
import ru.fefu.courseproject_garmentfactory.api.models.Order
import ru.fefu.courseproject_garmentfactory.api.models.Product
import ru.fefu.courseproject_garmentfactory.databinding.FragmentFittingsBinding

class OrdersFragment : Fragment() {
    private var _binding: FragmentFittingsBinding? = null
    private val binding get() = _binding!!
    private var orders = mutableListOf<Order>()
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
        getOrders()
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {
            App.orderCurrentSelected = adapter.getItemById(it) // Макс отказался делать гет запрос на получение заказа по id =)
            findNavController().navigate(R.id.action_navigation_orders_to_orderDetailsFragment,arguments)
        }

    }
    private fun getOrders(){
        App.getApi.getOrderList(App.getToken()).enqueue(object : Callback<List<Order>> {
            override fun onResponse(
                call: Call<List<Order>>,
                response: Response<List<Order>>
            ) {
                if (response.isSuccessful) {
                    var isNew = false
                    Log.i("success get orders", response.body().toString())
                    val body = response.body()
                    body?.forEach{
                        if (!orders.contains(it)) {
                            orders.add(it)
                            isNew = true
                        }
                    }
                    if (isNew) {
                        adapter.notifyDataSetChanged()
                    }

                }
                else {
                    Log.e("get list clothes", "not auth")
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                Log.e("get list order", t.message.toString())
            }
        })
    }
}