package ru.fefu.courseproject_garmentfactory.ui.lists.tabs

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
import ru.fefu.courseproject_garmentfactory.ui.ListRecyclerViewAdapter
import ru.fefu.courseproject_garmentfactory.databinding.FragmentPruductsBinding

class ProductsFragment : Fragment() {
    private var _binding: FragmentPruductsBinding? = null
    private val binding get() = _binding!!
    private val items = mutableListOf<Product>()
    private val adapter = ListRecyclerViewAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPruductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        /*adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putInt("ActivityID",activities[it].id )
            arguments = bundle
            findNavController().navigate(R.id.action_workoutFragment_to_myActivityDetailsFragment,arguments)
        }*/
        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putInt("article",items[it].article )
            bundle.putString("name",items[it].name )
            bundle.putString("image",items[it].image )
            var accessoriesList = ""
            for(i in items[it].accessories){
                accessoriesList += if(it != items.size-1){
                    i.name
                }else{
                    i.name+"\n"
                }
            }
            var clothList = ""
            for(i in items[it].clothes){
                clothList += if(it != items.size-1){
                    i.name
                }else{
                    i.name+"\n"
                }
            }
            bundle.putInt("price",items[it].price)
            bundle.putString("comment",items[it].comment)
            bundle.putString("accessories",accessoriesList)
            bundle.putString("clothes",clothList)
            bundle.putInt("length",items[it].length)
            bundle.putInt("width",items[it].width)
            bundle.putInt("size",items[it].size)

            arguments = bundle
            findNavController().navigate(R.id.action_navigation_lists_to_productsInfoFragment,arguments)
        }
    }

    private fun getProducts() {
        App.getApi.getProductList(App.getToken()).enqueue(object :
            Callback<List<Product>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful) {
                    var isNew = false
                    Log.i("success get products", response.body().toString())
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
                    Log.e("getlistprod", "not auth")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.e("get products packs", t.message.toString())
            }
        })
    }
}