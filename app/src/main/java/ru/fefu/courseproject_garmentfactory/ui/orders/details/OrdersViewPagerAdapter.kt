package ru.fefu.courseproject_garmentfactory.ui.orders.details

import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs.OrderDetailsInfoFragment
import ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs.OrderDetailsMappingsFragment
import ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs.OrderDetailsProductsFragment
import androidx.fragment.app.Fragment as Fragment

class OrdersViewPagerAdapter(fragment: OrderDetailsFragment):FragmentStateAdapter(fragment) {
    private val mFragments: Array<Fragment> = arrayOf(
        OrderDetailsInfoFragment(),
        OrderDetailsProductsFragment(),
        OrderDetailsMappingsFragment()
    )
    val mFragmentNames: Array<String> = arrayOf(
        "Информация",
        "Товары",
        "Раскрой"
    )
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}
