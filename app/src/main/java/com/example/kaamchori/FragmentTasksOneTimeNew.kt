package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.ToggleButton
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText

class FragmentTasksOneTimeNew : Fragment() {

    private lateinit var etDescription : TextInputEditText
    private lateinit var dpStartDate : DatePicker
    private lateinit var dpEndDate : DatePicker
    private lateinit var etFrequency : TextInputEditText
    private lateinit var tbStatus : ToggleButton
    private lateinit var btSave : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modify_recurring_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etDescription = view.findViewById(R.id.modify_recurring_task_title_edit_text)
        dpStartDate = view.findViewById(R.id.modify_recurring_task_start_date)
        dpEndDate = view.findViewById(R.id.modify_recurring_task_end_date)
        etFrequency = view.findViewById(R.id.modify_recurring_task_frequency_edit_text)
        tbStatus = view.findViewById(R.id.modify_recurring_task_status)
        btSave = view.findViewById(R.id.modify_recurring_task_save)

        etFrequency.visibility = View.GONE

        btSave.setOnClickListener {
            val startDateStructure = StructureDateTime(
                dpStartDate.year,
                dpStartDate.month+1,
                dpStartDate.dayOfMonth
            )
            val startDateInstance = yearMonthDayToDate(startDateStructure)

            val endDateStructure = StructureDateTime(
                dpEndDate.year,
                dpEndDate.month+1,
                dpEndDate.dayOfMonth
            )

            val endDateInstance = yearMonthDayToDate(endDateStructure)

            val newOneTimeTask = StructureOneTimeTasks(
                etDescription.text.toString(),
                startDateInstance,
                endDateInstance,
                tbStatus.isChecked
            )

            GlobalVariables.oneTimeTasksList.add(0,newOneTimeTask)
            findNavController().navigate(
                R.id.oneTimeTasksFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.oneTimeTasksFragment, true) // Removes currentFragment from back stack
                    .build()
            )
        }

    }
    companion object {
        private val TAG = "FRAGMENT_TASKS_ONE_TIME_NEW"
    }
}