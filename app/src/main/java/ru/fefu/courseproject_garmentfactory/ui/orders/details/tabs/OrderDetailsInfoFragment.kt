package ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderDetailsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.orders.getStageText
import android.widget.Toast
import android.content.Context
import android.util.Log
import android.widget.PopupMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.models.ProductCountPair
import ru.fefu.courseproject_garmentfactory.api.models.StrResponse


class OrderDetailsInfoFragment : Fragment() {
    private var _binding: FragmentOrderDetailsInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.orderCurrentSelected.creation_date
        binding.code.text = App.orderCurrentSelected.id.toString()
        val date = App.orderCurrentSelected.creation_date.substring(0,10)
        binding.date.text = date
        binding.price.text = App.orderCurrentSelected.cost.toString()
        binding.status.text = getStageText(App.orderCurrentSelected.stage)
        binding.customerinfo.text = App.orderCurrentSelected.customer.name
        binding.managerphonenumber.text = App.orderCurrentSelected.manager.name

        val popupMenu = PopupMenu(
            context,binding.buttonContinue
        )
        popupMenu.menuInflater.inflate(R.menu.menu_popup,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            var id = menuItem.itemId
            if(id == R.id.menu1) id = 1
            if(id == R.id.menu2) id = 2
            if(id == R.id.menu3) id = 3
            if(id == R.id.menu4) id = 4
            App.getApi.changeOrderStatus(App.getToken(),App.orderCurrentSelected.id,id)
                .enqueue(object : Callback<StrResponse> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onResponse(
                        call: Call<StrResponse>,
                        response: Response<StrResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.i("success get products", response.body().toString())
                            val body = response.body()
                            binding.status.text = getStageText(id)
                        } else {
                            Log.e("change status", "not auth")
                        }
                    }

                    override fun onFailure(call: Call<StrResponse>, t: Throwable) {
                        Log.e("get products packs", t.message.toString())
                    }
                })
            false
        }
        binding.buttonContinue.setOnClickListener{
            popupMenu.show()
        }
    }
}