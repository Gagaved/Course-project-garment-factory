package ru.fefu.courseproject_garmentfactory.ui.lists

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.ui.lists.tabs.FittingsFragment
import ru.fefu.courseproject_garmentfactory.ui.lists.tabs.MaterialsFragment
import ru.fefu.courseproject_garmentfactory.ui.lists.tabs.ProductsFragment

class ListsViewPagerAdapter(fragment: ListsFragment) : FragmentStateAdapter(fragment) {
    private val mFragments: Array<Fragment>
    val mFragmentNames: Array<String>

    init {
        val fragments = mutableListOf<Fragment>()
        when (App.current_role) {
            2 -> fragments.add(ProductsFragment())
            3 -> {
                fragments.add(MaterialsFragment())
                fragments.add(FittingsFragment())
            }
            4 -> {
                fragments.add(MaterialsFragment())
                fragments.add(FittingsFragment())
            }
            5 -> {
                fragments.add(ProductsFragment())
                fragments.add(MaterialsFragment())
                fragments.add(FittingsFragment())
            }
        }
        val fragmentsNames = mutableListOf<String>()
        when (App.current_role) {
            2 -> fragmentsNames.add("Продукция")
            3 -> {
                fragmentsNames.add("Материалы")
                fragmentsNames.add("Фурнитура")
            }
            4 -> {
                fragmentsNames.add("Материалы")
                fragmentsNames.add("Фурнитура")
            }
            5 -> {
                fragmentsNames.add("Продукция")
                fragmentsNames.add("Материалы")
                fragmentsNames.add("Фурнитура")
            }
        }
        mFragments = fragments.toTypedArray()
        mFragmentNames = fragmentsNames.toTypedArray()
    }


    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}