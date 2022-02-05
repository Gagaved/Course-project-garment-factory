package ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.*
import ru.fefu.courseproject_garmentfactory.databinding.FragmentClothMapBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL
import kotlin.math.roundToInt

class ClothMapFragment : Fragment() {
    private var _binding: FragmentClothMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClothMapBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = requireArguments().getString("name")
        SetImageToViewFromURL(binding.image).execute("http://sewing.mrfox131.software/"+requireArguments().getString("image"))
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}