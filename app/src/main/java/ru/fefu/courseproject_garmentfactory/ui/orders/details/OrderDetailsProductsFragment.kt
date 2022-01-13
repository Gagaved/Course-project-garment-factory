package ru.fefu.courseproject_garmentfactory.ui.orders.details
import android.annotation.SuppressLint
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
import ru.fefu.courseproject_garmentfactory.api.models.Product
import ru.fefu.courseproject_garmentfactory.api.models.ProductCountPair
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderDetailsProductsBinding
import ru.fefu.courseproject_garmentfactory.ui.ListRecyclerViewAdapter

class OrderDetailsProductsFragment : Fragment() {
    private var _binding: FragmentOrderDetailsProductsBinding? = null
    private val binding get() = _binding!!
    private var items = arrayListOf<Product>()
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
        getProducts()
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putInt("article",items[it].article )
            bundle.putString("name",items[it].name )
            bundle.putString("image",items[it].image )
            var accessoriesList = ""
            for(i in items[it].accessories){
                accessoriesList+=i.name+"\n"
            }
            var clothList = ""
            for(i in items[it].clothes){
                clothList+=i.name+"\n"
            }
            bundle.putString("accessories",accessoriesList)
            bundle.putString("clothes",clothList)
            bundle.putInt("length",items[it].length)
            bundle.putInt("width",items[it].width)
            arguments = bundle
            findNavController().navigate(R.id.action_navigation_orders_to_orderProductInfoFragment,arguments)
        }
    }
    private fun getProducts() {
        App.getApi.getProductByOrder(App.getToken(),App.orderCurrentSelected.id).enqueue(object : Callback<List<ProductCountPair>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ProductCountPair>>,
                response: Response<List<ProductCountPair>>
            ) {
                if (response.isSuccessful) {
                    var isNew = false
                    Log.i("success get products", response.body().toString())
                    val body = response.body()
                    body?.forEach{
                        if (!items.contains(it.product)) {
                            it.product.count = it.count
                            items.add(it.product)
                            isNew = true
                        }
                    }
                    App.orderCurrentSelected.products = items
                    if (isNew) {
                        adapter.notifyDataSetChanged()
                    }
                }
                else {
                    Log.e("getlistprod", "not auth")
                }
            }

            override fun onFailure(call: Call<List<ProductCountPair>>, t: Throwable) {
                Log.e("get products packs", t.message.toString())
            }
        })
    }
}