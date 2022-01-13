package ru.fefu.courseproject_garmentfactory.ui.lists.details

import ListViewAdapterProducts
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Product
import ru.fefu.courseproject_garmentfactory.databinding.FragmentMaterialsInfoBinding
import ru.fefu.courseproject_garmentfactory.databinding.HeaderMaterialsInfoBinding
import ru.fefu.courseproject_garmentfactory.databinding.HeaderProductsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL


class ProductsInfoFragment : Fragment() {
    private var _binding: FragmentMaterialsInfoBinding? = null
    private val binding get() = _binding!!
    private val historyChangeList = arrayListOf<Product>()
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
    private fun createHeader():View{
        binding.toolbar.title = requireArguments().getString("name")
        var headerBinding: HeaderProductsInfoBinding = HeaderProductsInfoBinding.inflate(layoutInflater)
        headerBinding.code.text = requireArguments().getInt("article").toString()
        headerBinding.width.text = requireArguments().getInt("width").toString()
        headerBinding.fittingslist.text = requireArguments().getString("accessories")
        headerBinding.fablricslist.text = requireArguments().getString("clothes")
        headerBinding.width.text = requireArguments().getInt("width").toString()
        headerBinding.length.text = requireArguments().getInt("length").toString()
        SetImageToViewFromURL(headerBinding.image).execute("http://sewing.mrfox131.software/"+requireArguments().getString("image"))

        return headerBinding.root
    }
    private fun getHistoryChange() {
        App.getApi.getPreviousProductList(App.getToken(),requireArguments().getInt("article")).enqueue(object :
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
                        if (!historyChangeList.contains(it)) {
                            historyChangeList.add(it)
                            isNew = true
                        }
                    }
                    if (isNew) {
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        getHistoryChange()
        binding.listview.adapter = context?.let { ListViewAdapterProducts(it,historyChangeList) }
        binding.listview.addHeaderView(createHeader(), null, false)
    }
}