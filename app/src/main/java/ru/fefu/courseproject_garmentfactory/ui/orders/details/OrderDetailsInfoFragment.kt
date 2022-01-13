package ru.fefu.courseproject_garmentfactory.ui.lists.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderDetailsInfoBinding
import ru.fefu.courseproject_garmentfactory.ui.orders.getStageText
import java.text.SimpleDateFormat

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
    }
}