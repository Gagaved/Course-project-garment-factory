package ru.fefu.courseproject_garmentfactory.ui.lists.details

import ListViewAdapterMaterials
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
import ru.fefu.courseproject_garmentfactory.api.models.ClothPack
import ru.fefu.courseproject_garmentfactory.databinding.FragmentMaterialsInfoBinding
import ru.fefu.courseproject_garmentfactory.databinding.HeaderMaterialsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL


class MaterialsInfoFragment : Fragment() {
    private var _binding: FragmentMaterialsInfoBinding? = null
    private val binding get() = _binding!!
    private val rollList = arrayListOf<ClothPack>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val newHeader: View = createHeader()
        getClothPacks()
        binding.listview.addHeaderView(newHeader, null, true)
    }

    private fun createHeader(): View {
        binding.toolbar.title = requireArguments().getString("name")
        val headerBinding: HeaderMaterialsInfoBinding =
            HeaderMaterialsInfoBinding.inflate(layoutInflater)
        headerBinding.code.text = requireArguments().getInt("article").toString()
        headerBinding.width.text = requireArguments().getDouble("width").toString()
        headerBinding.print.text = requireArguments().getString("print")
        headerBinding.comp.text = requireArguments().getString("composition")
        headerBinding.price.text = requireArguments().getInt("price").toString()
        headerBinding.color.text = requireArguments().getString("color")
        SetImageToViewFromURL(headerBinding.image).execute(
            "http://sewing.mrfox131.software/" + requireArguments().getString(
                "image"
            )
        )
        return headerBinding.root
    }

    private fun getClothPacks() {
        App.getApi.getClothPacks(App.getToken(), requireArguments().getInt("article"))
            .enqueue(object : Callback<List<ClothPack>> {
                override fun onResponse(
                    call: Call<List<ClothPack>>,
                    response: Response<List<ClothPack>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("success get clothes", response.body().toString())
                        val body = response.body()
                        body?.forEach {
                            if (!rollList.contains(it)) {
                                rollList.add(it)
                            }
                        }
                        binding.listview.adapter = context?.let {
                            ListViewAdapterMaterials(
                                it,
                                rollList,
                                requireArguments().getInt("article")
                            )
                        }
                    } else {
                        Log.e("get list clothes", "not auth")
                    }
                }

                override fun onFailure(call: Call<List<ClothPack>>, t: Throwable) {
                    Log.e("get cloth packs", t.message.toString())
                }
            })
    }
}