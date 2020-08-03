package com.example.kotlin_shopping_list_cooroutine.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.di.AppModule
import com.example.kotlin_shopping_list_cooroutine.di.RepoModule
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity(), IMainActivity {

    private var navController: NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf(AppModule, RepoModule))
        }
    }

    override fun onStart() {
        super.onStart()
        inflateNavigation()
    }

    private fun inflateNavigation() {
        navController = findNavController(R.id.nav_host_fragment)

        navController!!.graph =
            navController!!.navInflater.inflate(R.navigation.navigation_graph)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.splashFragment,
            R.id.shoppingListDashboardFragment
        ).build()

        navController?.let{
            NavigationUI.setupWithNavController(navToolbar, it, appBarConfiguration)
        }
    }

    override fun setToolbarTitle(text: String) {
        toolbarTitle.text = text
    }

    override fun setToolbarVisibility(visibility: Int) {
        navToolbar.visibility = visibility
    }
}