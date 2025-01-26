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
            StructureRecurringTasks(
                taskDescription = taskDescriptions[Random.nextInt(taskDescriptions.size)],
                startDate = StructureDateTime(), // Random start date within last 24 hours
                endDate = StructureDateTime(), // Random end date within next 7 days
                frequency = Random.nextInt(10,100),
                status = Random.nextBoolean() // Randomly set status as completed or not
            )
        }
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
                startDate = StructureDateTime(), // Random start date within last 24 hours
                endDate = StructureDateTime(), // Random end date within next 7 days
                status = Random.nextBoolean() // Randomly set status as completed or not
            )
        }
    }

    fun generateMultipleTasks(): MutableList<StructureMultipleTasks> {
        val tasks = mutableListOf<StructureMultipleTasks>()

        repeat(10) {
            val taskDescription = "Task ${it + 1}"
            val startDate = StructureDateTime()
            val endDate = StructureDateTime() // Up to 10 days from now
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