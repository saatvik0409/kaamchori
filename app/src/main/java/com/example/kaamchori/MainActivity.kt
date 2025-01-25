package com.example.kaamchori

import android.os.Bundle
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
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: LayoutMainActivityBinding
    private lateinit var navController: NavController
    public  var recurringTasksList = listOf<StructureRecurringTasks>()

    companion object {
        private var TAG = "MAIN_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the NavHostFragment and get its NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        // Get reference to the BottomNavigationView from the layout
        val navView: BottomNavigationView = binding.bottomNavView
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Set item icon tint list to null (optional, to disable default icon tinting)
//        navView.itemIconTintList = null

        // Set up the BottomNavigationView with the NavController for navigation
        navView.setupWithNavController(navController)
        GlobalVariables.recurringTasksList = generateRecurringTasks()
        GlobalVariables.oneTimeTasksList = generateRandomTasks()
        GlobalVariables.multipleTasksList = generateMultipleTasks()
    }


    fun generateRecurringTasks(): MutableList<StructureRecurringTasks> {
        return mutableListOf(
            StructureRecurringTasks(
                taskDescription = "Water the plants",
                startDate = Date(), // Current date
                endDate = Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000), // 7 days from now
                frequency = 24, // Every 24 hours
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Take out the trash",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000), // 30 days from now
                frequency = 168, // Weekly (168 hours)
                status = false
            ),
            StructureRecurringTasks(
                taskDescription = "Go jogging",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000), // 14 days from now
                frequency = 48, // Every 48 hours
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Clean the house",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 21 * 24 * 60 * 60 * 1000), // 21 days from now
                frequency = 168, // Weekly
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Pay electricity bill",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 60 * 24 * 60 * 60 * 1000), // 60 days from now
                frequency = 720, // Monthly (approx 720 hours)
                status = false
            ),
            StructureRecurringTasks(
                taskDescription = "Check email",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000), // 7 days from now
                frequency = 12, // Every 12 hours
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Grocery shopping",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 28 * 24 * 60 * 60 * 1000), // 28 days from now
                frequency = 168, // Weekly
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Visit dentist",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 90 * 24 * 60 * 60 * 1000), // 90 days from now
                frequency = 2160, // Quarterly (approx 2160 hours)
                status = false
            )
        )
    }

    fun generateRandomTasks(): MutableList<StructureOneTimeTasks> {
        val taskDescriptions = listOf(
            "Write a report",
            "Attend a meeting",
            "Complete a project",
            "Make a phone call",
            "Send an email",
            "Schedule a doctor's appointment",
            "Go grocery shopping",
            "Exercise",
            "Read a book",
            "Clean the house"
        )

        return MutableList(10) {
            StructureOneTimeTasks(
                taskDescription = taskDescriptions[Random.nextInt(taskDescriptions.size)],
                startDate = Date(System.currentTimeMillis() - Random.nextInt(1000 * 60 * 60 * 24)), // Random start date within last 24 hours
                endDate = Date(System.currentTimeMillis() + Random.nextInt(1000 * 60 * 60 * 24 * 7)), // Random end date within next 7 days
                status = Random.nextBoolean() // Randomly set status as completed or not
            )
        }
    }

    fun generateMultipleTasks(): MutableList<StructureMultipleTasks> {
        val tasks = mutableListOf<StructureMultipleTasks>()

        repeat(10) {
            val taskDescription = "Task ${it + 1}"
            val startDate = Date()
            val endDate = Date(System.currentTimeMillis() + Random.nextInt(10) * (24 * 60 * 60 * 1000L)) // Up to 10 days from now
            val totalQty = Random.nextInt(100) + 1  // Random quantity between 1 and 100
            val status = Random.nextInt(2)  // 0 or 1, assuming 0 = not completed, 1 = completed

            tasks.add(
                StructureMultipleTasks(
                    taskDescription = taskDescription,
                    startDate = startDate,
                    endDate = endDate,
                    totalQty = totalQty,
                    status = status
                )
            )
        }

        return tasks
    }

}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KaamchoriTheme {
//        Greeting("Android")
//    }
//}