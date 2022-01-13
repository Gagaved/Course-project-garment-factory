package ru.fefu.courseproject_garmentfactory.ui.lists.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.*
import ru.fefu.courseproject_garmentfactory.databinding.FragmentFittingsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL
class FittingsInfoFragment : Fragment() {
    private var _binding: FragmentFittingsInfoBinding? = null
    private val binding get() = _binding!!
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFittingsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun getAccessoryCount() {
        App.getApi.getAccessoryPack(App.getToken(),requireArguments().getInt("article")).enqueue(object : Callback<AccessoryPack> {
            override fun onResponse(
                call: Call<AccessoryPack>,
                response: Response<AccessoryPack>
            ) {
                if (response.isSuccessful) {
                    Log.i("success get clothes", response.body().toString())
                    count = response.body()?.count?:0
                    binding.count.text = count.toString()
                }
                else {
                    Log.e("getaccessorypack", "not auth")
                }
            }
            override fun onFailure(call: Call<AccessoryPack>, t: Throwable) {
                Log.e("getaccessorypack", t.message.toString())
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = requireArguments().getString("name")
        binding.code.text = requireArguments().getInt("article").toString()
        binding.type.text = requireArguments().getString("type")
        binding.width.text = requireArguments().getInt("width").toString()
        binding.length.text = requireArguments().getInt("length").toString()
        binding.weight.text = requireArguments().getInt("weight").toString()
        binding.price.text = requireArguments().getInt("price").toString()
        SetImageToViewFromURL(binding.image).execute("http://sewing.mrfox131.software/"+requireArguments().getString("image"))
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val input = binding.decommissionInput.text
        getAccessoryCount()
        if (App.current_role == 3 || App.current_role == 5) {
            binding.buttonDecommission.setOnClickListener {
                App.getApi.accessoryDecommission(
                    App.getToken(),
                    requireArguments().getInt("article"),
                    (input.toString()).toInt()
                ).enqueue(object : Callback<AccessoryDecommissionResponse> {
                    override fun onFailure(
                        call: Call<AccessoryDecommissionResponse>,
                        t: Throwable
                    ) {
                        Log.i("fail clothDecommission", t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<AccessoryDecommissionResponse>,
                        response: Response<AccessoryDecommissionResponse>
                    ) {
                        val textError: TextView
                        if (response.isSuccessful) {
                            val body = response.body()
                            getAccessoryCount()
                            Log.i("succ.AccessoryDecomm.", input.toString())
                        } else {
                            when (response.code()) {
                                401 -> {
                                    //textError.text = "Неправильная связка логин-пароль, проверьте правильность введённых данных"
                                }
                                else -> {
                                    //textError.text = response.message()
                                }
                            }
                        }
                    }
                })
            }
        } else {
            binding.buttonDecommission.visibility = View.GONE
            binding.decommissionInput.visibility = View.GONE
        }
        //setCount()
    }
}