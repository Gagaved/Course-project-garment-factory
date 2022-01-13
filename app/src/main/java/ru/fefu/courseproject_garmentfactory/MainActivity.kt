package ru.fefu.courseproject_garmentfactory

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        val appBarConfiguration = createAppBarConfiguration()
//        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)

        when (App.current_role) {
            1 -> {
                navView.menu.removeItem(R.id.navigation_orders)
                navView.menu.removeItem(R.id.navigation_lists)
            }
            3 -> {navView.menu.removeItem(R.id.navigation_orders)}
            4 -> {navView.menu.removeItem(R.id.navigation_orders)}
        }
    }

    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    private fun createAppBarConfiguration() : AppBarConfiguration{
        val navSet = mutableSetOf(R.id.navigation_profile, R.id.navigation_lists)
        when (App.current_role) {
            2 -> {
                navSet.add(R.id.navigation_orders)
            }
            5 -> {
                navSet.add(R.id.navigation_orders)
            }
            else -> {}
        }
        return AppBarConfiguration(navSet)
    }
}