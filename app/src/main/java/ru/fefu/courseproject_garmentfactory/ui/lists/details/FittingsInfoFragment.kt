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
    var list = mutableListOf<AccessoriesPacks>()
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
        binding.buttonDecommission.setOnClickListener(decommissionOnClickListener)
        setCount()
    }
    private fun setCount() {
        App.getApi.getAccessoryPacks(App.getToken(),requireArguments().getInt("article")).enqueue(object : Callback<List<AccessoriesPacks>> {
            override fun onResponse(
                call: Call<List<AccessoriesPacks>>,
                response: Response<List<AccessoriesPacks>>
            ) {
                if (response.isSuccessful) {
                    var count = 0
                    Log.i("success get acc.packs", response.body().toString())
                    val body = response.body()
                    body?.forEach{
                            count+=it.count
                            list.add(it);
                    }
                    binding.count.text = count.toString()
                }
                else {
                    Log.e("get accessoriesPack", "not auth")
                }
            }

            override fun onFailure(call: Call<List<AccessoriesPacks>>, t: Throwable) {
                Log.e("get list accessories", t.message.toString())
            }
        })
    }
    private val decommissionOnClickListener = View.OnClickListener {
        val countDec:Int = binding.decommissionInput.text.toString().toInt()
        var count = binding.count.text.toString().toInt();
        if(countDec<=count && count >0){
            for (i in list.indices) {
                if(list[i].count>=countDec) {
                    list[i].count-=countDec
                    count-=countDec
                    App.getApi.accessoryDecommission(AccessoryDecommission(list[i].batch,list[i].article,countDec)).enqueue(object: Callback<AccessoryDecommission> {
                        override fun onFailure(call: Call<AccessoryDecommission>, t: Throwable) {
                            Log.i("failLogin", t.message.toString())
                        }
                        override fun onResponse(call: Call<AccessoryDecommission>, response: Response<AccessoryDecommission>) {
                            //val textError: TextView = binding.errorText
                            if (response.isSuccessful) {
                                val body = response.body()
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
                    break
                }else{
                    App.getApi.accessoryDecommission(AccessoryDecommission(list[i].batch,list[i].article,list[i].count)).enqueue(object: Callback<AccessoryDecommission> {
                        override fun onFailure(call: Call<AccessoryDecommission>, t: Throwable) {
                            Log.i("failLogin", t.message.toString())
                        }
                        override fun onResponse(call: Call<AccessoryDecommission>, response: Response<AccessoryDecommission>) {
                           //val textError: TextView = binding.errorText
                            if (response.isSuccessful) {
                                val body = response.body()
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
                    count-=list[i].count
                    list[i].count = 0
                }

            }
    }else{
        }
        setCount()
    }
}