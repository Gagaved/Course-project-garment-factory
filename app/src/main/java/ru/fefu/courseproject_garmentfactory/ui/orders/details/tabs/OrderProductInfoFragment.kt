package ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Product
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderProductInfoBinding
import ru.fefu.courseproject_garmentfactory.databinding.HeaderProductsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL

class OrderProductInfoFragment : Fragment() {
    private var _binding: FragmentOrderProductInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var product : Product
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderProductInfoBinding.inflate(inflater, container, false)

        //val textView: TextView = binding.textLists
        /*listsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }
        getProducts()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun getProducts(){
        App.getApi.getProductByArticle(App.getToken(), requireArguments().getInt("article")).enqueue(object : Callback<Product> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                if (response.isSuccessful) {
                    Log.i("success get products", response.body().toString())
                    product = response.body()!!

                } else {
                    Log.e("getlistprod", "not auth")
                }
                setViewData()
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e("get products packs", t.message.toString())
            }
            private fun setViewData(){
                var accessoriesList = ""
                for(i in product.accessories){
                    accessoriesList+=i.name+"\n"
                }
                var clothList = ""
                for(i in product.clothes){
                    clothList+=i.name+"\n"
                }
                binding.toolbar.title = product.name.toString()
                binding.code.text = product.id.toString()
                binding.width.text = product.width.toString()
                binding.fittingslist.text = accessoriesList
                binding.fablricslist.text = clothList
                binding.length.text = product.length.toString()
                SetImageToViewFromURL( binding.image).execute("http://sewing.mrfox131.software/"+product.image.toString())
            }
        })
    }
}