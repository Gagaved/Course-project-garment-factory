package ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.fefu.courseproject_garmentfactory.databinding.FragmentClothMapBinding
import ru.fefu.courseproject_garmentfactory.ui.SetImageToViewFromURL

class ClothMapFragment : Fragment() {
    private var _binding: FragmentClothMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = requireArguments().getString("name")
        SetImageToViewFromURL(binding.image).execute(
            "http://sewing.mrfox131.software/" + requireArguments().getString(
                "image"
            )
        )
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}