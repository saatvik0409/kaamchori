package com.example.kaamchori

import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kaamchori.databinding.LayoutMainActivityBinding
import com.example.kaamchori.ui.theme.KaamchoriTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kaamchori.database.DatabaseManager
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: LayoutMainActivityBinding
    private lateinit var navController: NavController
    public var recurringTasksList = listOf<StructureRecurringTasks>()

    companion object {
        private var TAG = "MAIN_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.bottomNavView
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dbManager = DatabaseManager(this)
        dbManager.fetchAllData()

        navView.setupWithNavController(navController)

    }
}
