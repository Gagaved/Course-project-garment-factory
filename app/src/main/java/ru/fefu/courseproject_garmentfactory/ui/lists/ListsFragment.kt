package ru.fefu.courseproject_garmentfactory.ui.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.courseproject_garmentfactory.databinding.FragmentListsBinding

class ListsFragment : Fragment() {
    private var _binding: FragmentListsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mViewPager = binding.listsViewPager2
        mViewPager.adapter = (ListsViewPagerAdapter(this))
        val tabLayout = binding.listsTabLayout
        TabLayoutMediator(
            tabLayout,
            mViewPager
        ) { tab, position ->
            tab.text =
                ((mViewPager.adapter) as ListsViewPagerAdapter?)!!.mFragmentNames[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}