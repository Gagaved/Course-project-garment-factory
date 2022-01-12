package ru.fefu.courseproject_garmentfactory.ui.lists.details

import ListViewAdapterMaterials
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import ru.fefu.courseproject_garmentfactory.databinding.FragmentMaterialsInfoBinding
import android.R
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Cloth
import ru.fefu.courseproject_garmentfactory.api.models.ClothPack
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL
import ru.fefu.courseproject_garmentfactory.databinding.HeaderMaterialsInfoBinding as HeaderMaterialsInfoBinding


class MaterialsInfoFragment : Fragment() {
    private var _binding: FragmentMaterialsInfoBinding? = null
    private val binding get() = _binding!!
    private val rollList = arrayListOf<ClothPack>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val inflater = layoutInflater
        //val myHeader = inflater.inflate(ru.fefu.courseproject_garmentfactory.R.layout.header_materials_info,binding.listview, false) as ViewGroup
        val newHeader: View = createHeader()
        getClothPacks()
        binding.listview.addHeaderView(newHeader, null, false)
        binding.listview.adapter = context?.let { ListViewAdapterMaterials(it,rollList) }
    }
    private fun createHeader():View{
        binding.toolbar.title = requireArguments().getString("name")
        var headerBinding: HeaderMaterialsInfoBinding = HeaderMaterialsInfoBinding.inflate(layoutInflater)
        headerBinding.code.text = requireArguments().getInt("article").toString()
        headerBinding.width.text = requireArguments().getInt("width").toString()
        headerBinding.print.text = requireArguments().getString("print")
        headerBinding.comp.text = requireArguments().getString("composition")
        headerBinding.price.text = requireArguments().getInt("price").toString()
        headerBinding.color.text = requireArguments().getString("color")
        SetImageToViewFromURL(headerBinding.image).execute("http://sewing.mrfox131.software/"+requireArguments().getString("image"))

        return headerBinding.root
    }
    private fun getClothPacks() {
        App.getApi.getClothPacks(App.getToken(),requireArguments().getInt("article")).enqueue(object : Callback<List<ClothPack>> {
            override fun onResponse(
                call: Call<List<ClothPack>>,
                response: Response<List<ClothPack>>
            ) {
                if (response.isSuccessful) {
                    var isNew = false
                    Log.i("success get clothes", response.body().toString())
                    val body = response.body()
                    body?.forEach{
                        if (!rollList.contains(it)) {
                            rollList.add(it)
                            isNew = true
                        }
                    }
                    if (isNew) {
                    }

                }
                else {
                    Log.e("get list clothes", "not auth")
                }
            }
            override fun onFailure(call: Call<List<ClothPack>>, t: Throwable) {
                Log.e("get cloth packs", t.message.toString())
            }
        })
    }
}