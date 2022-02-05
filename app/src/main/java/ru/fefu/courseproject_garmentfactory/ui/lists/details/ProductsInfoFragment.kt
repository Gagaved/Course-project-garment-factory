package ru.fefu.courseproject_garmentfactory.ui.lists.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Product
import ru.fefu.courseproject_garmentfactory.databinding.FragmentProductsInfoBinding
import ru.fefu.courseproject_garmentfactory.databinding.HeaderProductsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL


class ProductsInfoFragment : Fragment() {
    private var _binding: FragmentProductsInfoBinding? = null
    private val binding get() = _binding!!
    private val historyChangeList = arrayListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createHeader(): View {
        binding.toolbar.title = requireArguments().getString("name")
        val headerBinding: HeaderProductsInfoBinding =
            HeaderProductsInfoBinding.inflate(layoutInflater)
        headerBinding.code.text = requireArguments().getInt("article").toString()
        headerBinding.price.text = requireArguments().getInt("price").toString()
        headerBinding.comment.text = requireArguments().getString("comment")
        headerBinding.width.text = requireArguments().getInt("width").toString()
        headerBinding.fittingslist.text = requireArguments().getString("accessories")
        headerBinding.size.text = requireArguments().getInt("size").toString()
        headerBinding.fablricslist.text = requireArguments().getString("clothes")
        headerBinding.width.text = requireArguments().getInt("width").toString()
        headerBinding.length.text = requireArguments().getInt("length").toString()
        SetImageToViewFromURL(headerBinding.image).execute(
            "http://sewing.mrfox131.software/" + requireArguments().getString(
                "image"
            )
        )
        if (historyChangeList.isEmpty()) {
            headerBinding.changeTitle.text = "Нет истории изменений"

        }
        return headerBinding.root
    }

    private fun getHistoryChange() {
        App.getApi.getPreviousProductList(App.getToken(), requireArguments().getInt("article"))
            .enqueue(object :
                Callback<List<Product>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("success get products", response.body().toString())
                        val body = response.body()
                        body?.forEach {
                            if (!historyChangeList.contains(it)) {
                                historyChangeList.add(it)
                            }
                        }
                    } else {
                        Log.e("getlistprod", "not auth")
                    }
                    binding.listview.addHeaderView(createHeader(), null, false)
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
        binding.listview.adapter = context?.let { ListViewAdapterProducts(it, historyChangeList) }
    }
}