package ru.fefu.courseproject_garmentfactory.ui.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.courseproject_garmentfactory.databinding.FragmentListsBinding

class ListsFragment : Fragment() {

    private lateinit var listsViewModel: ListsViewModel
    private var _binding: FragmentListsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listsViewModel =
            ViewModelProvider(this).get(ListsViewModel::class.java)

        _binding = FragmentListsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textLists
        /*listsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mViewPager = binding.listsViewPager2
        mViewPager.adapter=(ListsViewPagerAdapter(this))
        val tabLayout = binding.listsTabLayout
        TabLayoutMediator(tabLayout,mViewPager

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