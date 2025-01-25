package com.example.kaamchori

import android.app.ActivityManager.TaskDescription
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.kaamchori.models.StructureDate
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.getYearMonthDay
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText


class FragmentTasksMultipleModify : Fragment() {

    private var indexMultipleTask : Int = 0

    private lateinit var thisMultipleTask : StructureMultipleTasks
    private lateinit var etTaskDescription: TextInputEditText
    private lateinit var etCompleted : TextInputEditText
    private lateinit var etTotal : TextInputEditText
    private lateinit var startDatePicker: DatePicker
    private lateinit var endDatePicker: DatePicker
    private lateinit var btSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {it ->
            indexMultipleTask = it.getInt("indexMultipleTask")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modify_tasks_multiple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etTaskDescription = view.findViewById(R.id.modify_recurring_task_title_edit_text)
        startDatePicker = view.findViewById(R.id.modify_recurring_task_start_date)
        endDatePicker = view.findViewById(R.id.modify_recurring_task_end_date)
        etCompleted = view.findViewById(R.id.modify_multiple_completed_edit_text)
        etTotal = view.findViewById(R.id.modify_multiple_total_edit_text)
        btSave = view.findViewById(R.id.modify_recurring_task_save)

        thisMultipleTask = GlobalVariables.multipleTasksList[indexMultipleTask]

        etTaskDescription.setText(thisMultipleTask.taskDescription)
        val startDate = getYearMonthDay(thisMultipleTask.startDate)
        val endDate = getYearMonthDay(thisMultipleTask.endDate)

        startDatePicker.updateDate(
            startDate.year,startDate.month,startDate.day
        )
        endDatePicker.updateDate(
            endDate.year,endDate.month,endDate.day
        )

        etCompleted.setText(thisMultipleTask.status.toString())
        etTotal.setText(thisMultipleTask.totalQty.toString())

        btSave.setOnClickListener {
            val startDateStructure = StructureDate(
                startDatePicker.year,
                startDatePicker.month+1,
                startDatePicker.dayOfMonth
            )
            val startDateInstance = yearMonthDayToDate(startDateStructure)

            val endDateStructure = StructureDate(
                endDatePicker.year,
                endDatePicker.month+1,
                endDatePicker.dayOfMonth
            )
            val endDateInstance = yearMonthDayToDate(endDateStructure)

            val newMultipleTask = StructureMultipleTasks(
                etTaskDescription.text.toString(),
                startDateInstance,
                endDateInstance,
                etTotal.text.toString().toInt(),
                etCompleted.text.toString().toInt()
            )
            GlobalVariables.multipleTasksList[indexMultipleTask] = newMultipleTask
            findNavController().navigate(
                R.id.multipleTasksFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.multipleTasksFragmentModify, true) // Removes currentFragment from back stack
                    .build()
            )
        }


    }

    companion object {
        private val TAG = "FRAGMENT_TASKS_MULTIPLE_MODIFY"
    }
}