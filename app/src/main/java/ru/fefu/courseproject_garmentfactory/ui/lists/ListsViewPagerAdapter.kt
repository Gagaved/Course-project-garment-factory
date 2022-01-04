package ru.fefu.courseproject_garmentfactory.ui.lists

import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.courseproject_garmentfactory.ui.lists.tabs.ProductsFragment
import ru.fefu.courseproject_garmentfactory.ui.lists.tabs.MaterialsFragment
import ru.fefu.courseproject_garmentfactory.ui.lists.tabs.FittingsFragment
import androidx.fragment.app.Fragment as Fragment

class ListsViewPagerAdapter(fragment: ListsFragment):FragmentStateAdapter(fragment) {
    private val mFragments: Array<Fragment> = arrayOf(
        ProductsFragment(),
        MaterialsFragment(),
        FittingsFragment()
    )
    val mFragmentNames: Array<String> = arrayOf(
        "Выпускаемая продукция",
        "Материалы",
        "Фурнитура"
    )
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}