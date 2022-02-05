package ru.fefu.courseproject_garmentfactory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        navView.setupWithNavController(navController)

        when (App.current_role) {
            1 -> {
                navView.menu.removeItem(R.id.navigation_orders)
                navView.menu.removeItem(R.id.navigation_lists)
            }
            3 -> {
                navView.menu.removeItem(R.id.navigation_orders)
            }
            4 -> {
                navView.menu.removeItem(R.id.navigation_orders)
            }
        }
    }
}