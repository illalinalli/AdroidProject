package ru.alinas.cats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.alinas.cats.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        setupWithNavController(binding.bottomNavigationView, navController)
        val navigationGraph =
            navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph)
        navigationGraph.setStartDestination(R.id.randomCatFragment)
        navController.graph = navigationGraph
    }
}