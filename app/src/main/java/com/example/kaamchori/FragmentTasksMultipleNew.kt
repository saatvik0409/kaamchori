package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText


class FragmentTasksMultipleNew : Fragment() {

    private lateinit var etTaskDescription: TextInputEditText
    private lateinit var etCompleted : TextInputEditText
    private lateinit var etTotal : TextInputEditText
    private lateinit var startDatePicker: DatePicker
    private lateinit var endDatePicker: DatePicker
    private lateinit var btSave : Button
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

        btSave.setOnClickListener {
            val startDateStructure = StructureDateTime(
                startDatePicker.year,
                startDatePicker.month+1,
                startDatePicker.dayOfMonth
            )
            val startDateInstance = yearMonthDayToDate(startDateStructure)

            val endDateStructure = StructureDateTime(
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
            GlobalVariables.multipleTasksList.add(0,newMultipleTask)
            findNavController().navigate(
                R.id.multipleTasksFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.multipleTasksFragment, true) // Removes currentFragment from back stack
                    .build()
            )
        }

    }

    companion object {
        private val TAG = "FRAGMENT_TASKS_MULTIPLE_NEW"
    }
}