package ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Mapping
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderDetailsMappingsBinding


class OrderDetailsMappingsFragment : Fragment() {
    private var _binding: FragmentOrderDetailsMappingsBinding? = null
    private val binding get() = _binding!!
    private var items = arrayListOf<Mapping>()
    private val adapter = MappingListRecyclerAdapter(items)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailsMappingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapping()
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putString("name", items[it].article.toString())
            bundle.putString("image", items[it].map)
            arguments = bundle
            findNavController().navigate(
                R.id.action_orderDetailsFragment_to_clothMapFragment,
                arguments
            )
        }
    }

    private fun getMapping() {
        App.getApi.getClothMappings(App.getToken(), App.orderCurrentSelected.id)
            .enqueue(object : Callback<List<Mapping>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<Mapping>>,
                    response: Response<List<Mapping>>
                ) {
                    if (response.isSuccessful) {
                        var isNew = false
                        Log.i("success get mapping", response.body().toString())
                        val body = response.body()
                        body?.forEach {
                            var flag = false
                            for (i in items) {
                                if (it.article == i.article && it.batch_number == i.batch_number) {
                                    flag = true
                                }
                            }
                            if (!flag) {
                                items.add(it)
                                isNew = true

                            }
                        }
                        if (isNew) {
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.e("getlistmapp", "not auth")
                    }
                }

                override fun onFailure(call: Call<List<Mapping>>, t: Throwable) {
                    Log.e("get product maping", t.message.toString())
                }
            })
    }
}