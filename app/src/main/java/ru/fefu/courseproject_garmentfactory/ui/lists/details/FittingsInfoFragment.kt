package ru.fefu.courseproject_garmentfactory.ui.lists.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.AccessoryDecommissionResponse
import ru.fefu.courseproject_garmentfactory.api.models.AccessoryPack
import ru.fefu.courseproject_garmentfactory.databinding.FragmentFittingsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL
import kotlin.math.roundToInt

class FittingsInfoFragment : Fragment() {
    private var _binding: FragmentFittingsInfoBinding? = null
    private val binding get() = _binding!!
    var count: Int = 0
    private var isKilos: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFittingsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getAccessoryCount() {
        App.getApi.getAccessoryPack(App.getToken(), requireArguments().getInt("article"))
            .enqueue(object : Callback<AccessoryPack> {
                override fun onResponse(
                    call: Call<AccessoryPack>,
                    response: Response<AccessoryPack>
                ) {
                    if (response.isSuccessful) {
                        Log.i("success get clothes", response.body().toString())
                        count = response.body()?.amount ?: 0
                        binding.count.text = count.toString()
                    } else {
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
        binding.weight.text = requireArguments().getDouble("weight").toString()
        binding.price.text = requireArguments().getInt("price").toString()
        if (!requireArguments().getBoolean("kilos"))
            binding.radioGroup.visibility = View.GONE
        SetImageToViewFromURL(binding.image).execute(
            "http://sewing.mrfox131.software/" + requireArguments().getString(
                "image"
            )
        )
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            isKilos = when (checkedId) {
                R.id.kilos -> true
                else -> false
            }
        }
        val input = binding.decommissionInput.text
        getAccessoryCount()
        if (App.current_role == 3 || App.current_role == 5) {
            binding.buttonDecommission.setOnClickListener {
                if (!isKilos) {
                    App.getApi.accessoryDecommission(
                        App.getToken(),
                        requireArguments().getInt("article"),
                        (input.toString()).toDouble().roundToInt()
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
                } else {
                    App.getApi.accessoryDecommissionInKg(
                        App.getToken(),
                        requireArguments().getInt("article"),
                        (input.toString()).toDouble()
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
                                Toast.makeText(
                                    requireContext(),
                                    "Произошла ошибка списания",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }


            }
        } else {
            binding.buttonDecommission.visibility = View.GONE
            binding.decommissionInput.visibility = View.GONE
            binding.radioGroup.visibility = View.GONE
        }
        //setCount()
    }
}