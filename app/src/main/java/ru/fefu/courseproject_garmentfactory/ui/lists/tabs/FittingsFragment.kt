package ru.fefu.courseproject_garmentfactory.ui.lists.tabs

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
import ru.fefu.courseproject_garmentfactory.api.models.Accessories
import ru.fefu.courseproject_garmentfactory.databinding.FragmentFittingsBinding
import ru.fefu.courseproject_garmentfactory.ui.ListRecyclerViewAdapter

class FittingsFragment : Fragment() {
    private var _binding: FragmentFittingsBinding? = null
    private val binding get() = _binding!!
    private val items = mutableListOf<Accessories>()
    private val adapter = ListRecyclerViewAdapter(items)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFittingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putInt("article", items[it].article)
            bundle.putString("name", items[it].name)
            bundle.putString("image", items[it].image)
            bundle.putInt("width", items[it].width)
            bundle.putInt("length", items[it].width)
            bundle.putDouble("weight", items[it].weight)
            bundle.putInt("price", items[it].price)
            bundle.putString("type", items[it].type)
            bundle.putBoolean("kilos", items[it].kg_acceptable)
            arguments = bundle
            findNavController().navigate(
                R.id.action_navigation_lists_to_fittingsInfoFragment,
                arguments
            )
        }
        getFittings()
    }

    private fun getFittings() {
        App.getApi.getAccessoryList(App.getToken()).enqueue(object : Callback<List<Accessories>> {
            override fun onResponse(
                call: Call<List<Accessories>>,
                response: Response<List<Accessories>>
            ) {
                if (response.isSuccessful) {
                    var isNew = false
                    Log.i("success get accessories", response.body().toString())
                    val body = response.body()
                    body?.forEach {
                        if (!items.contains(it)) {
                            items.add(it)
                            isNew = true
                        }
                    }
                    if (isNew) {
                        adapter.notifyDataSetChanged()
                    }

                } else {
                    Log.e("get list accessories", "not auth")
                }
            }

            override fun onFailure(call: Call<List<Accessories>>, t: Throwable) {
                Log.e("get list accessories", t.message.toString())
            }
        })
    }
}