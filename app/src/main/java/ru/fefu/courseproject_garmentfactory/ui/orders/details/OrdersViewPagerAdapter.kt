package ru.fefu.courseproject_garmentfactory.ui.orders.details

import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.courseproject_garmentfactory.ui.lists.details.OrderDetailsInfoFragment
import androidx.fragment.app.Fragment as Fragment

class OrdersViewPagerAdapter(fragment: OrderDetailsFragment):FragmentStateAdapter(fragment) {
    private val mFragments: Array<Fragment> = arrayOf(
        OrderDetailsInfoFragment(),
        OrderDetailsProductsFragment(),
    )
    val mFragmentNames: Array<String> = arrayOf(
        "Информация",
        "Заказы",
    )
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}
