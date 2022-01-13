package ru.fefu.courseproject_garmentfactory.ui.orders.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.databinding.FragmentOrderDeteailsBinding
import ru.fefu.courseproject_garmentfactory.ui.lists.ListsViewPagerAdapter

class OrderDetailsFragment : Fragment() {
    private var _binding: FragmentOrderDeteailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDeteailsBinding.inflate(inflater, container, false)

        //val textView: TextView = binding.textLists
        /*listsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mViewPager = binding.listsViewPager2
        mViewPager.adapter=(OrdersViewPagerAdapter(this))
        val tabLayout = binding.listsTabLayout
        TabLayoutMediator(tabLayout,mViewPager

        ) { tab, position ->
            tab.text =
                ((mViewPager.adapter) as OrdersViewPagerAdapter?)!!.mFragmentNames[position]
        }.attach()

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbar.title = "Заказ номер "+App.orderCurrentSelected.id.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}