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
    //var list = mutableListOf<AccessoriesPacks>()
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
        //binding.buttonDecommission.setOnClickListener(decommissionOnClickListener)
        //setCount()
    }
}