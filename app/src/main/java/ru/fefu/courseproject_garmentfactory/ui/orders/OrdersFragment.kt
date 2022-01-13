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
        //fillDate()
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
    /*private fun getOrders(){
        App.getApi.getOrdersList(App.getToken()).enqueue(object : Callback<List<Cloth>> {
            override fun onResponse(
                call: Call<List<Cloth>>,
                response: Response<List<Cloth>>
            ) {
                if (response.isSuccessful) {
                    var isNew = false
                    Log.i("success get clothes", response.body().toString())
                    val body = response.body()
                    body?.forEach{
                        if (!items.contains(it)) {
                            items.add(it)
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

            override fun onFailure(call: Call<List<Cloth>>, t: Throwable) {
                Log.e("get list accessories", t.message.toString())
            }
        })
    }*/
}