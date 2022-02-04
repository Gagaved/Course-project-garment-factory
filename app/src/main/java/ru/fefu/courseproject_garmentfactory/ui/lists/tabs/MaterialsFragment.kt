package ru.fefu.courseproject_garmentfactory.ui.lists.tabs

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
import ru.fefu.courseproject_garmentfactory.api.models.Accessories
import ru.fefu.courseproject_garmentfactory.api.models.Cloth
import ru.fefu.courseproject_garmentfactory.ui.ListRecyclerViewAdapter
import ru.fefu.courseproject_garmentfactory.databinding.FragmentMaterialsBinding
import ru.fefu.courseproject_garmentfactory.ui.ItemListData

class MaterialsFragment : Fragment() {
    private var _binding: FragmentMaterialsBinding? = null
    private val binding get() = _binding!!
    private val items = mutableListOf<Cloth>()
    private val adapter = ListRecyclerViewAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMaterialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getClothes()
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putInt("article",items[it].article )
            bundle.putString("name",items[it].name )
            bundle.putString("image",items[it].image )
            bundle.putString("print",items[it].print )
            bundle.putString("composition",items[it].composition )
            bundle.putDouble("width",items[it].width )
            bundle.putInt("price",items[it].price )
            bundle.putString("color",items[it].color )
            arguments = bundle
            findNavController().navigate(R.id.action_navigation_lists_to_materialsInfoFragment,arguments)
        }
    }

    private fun getClothes() {
        App.getApi.getClothesList(App.getToken()).enqueue(object : Callback<List<Cloth>> {
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
    }
}