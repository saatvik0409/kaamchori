package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.kaamchori.database.DatabaseManager
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.DatePickerDialogOpener
import com.example.kaamchori.utils.TimePickerDialogOpener
import com.example.kaamchori.utils.compareDateTime
import com.example.kaamchori.utils.generateQueue
import com.example.kaamchori.utils.getDateString
import com.example.kaamchori.utils.getTimeString
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText


class FragmentTasksMultipleNew : Fragment() {

    private lateinit var etTaskDescription: TextInputEditText
    private lateinit var etCompleted : TextInputEditText
    private lateinit var etTotal : TextInputEditText
    private lateinit var startDatePicker: Button
    private lateinit var endDatePicker: Button
    private lateinit var tpStartTime : Button
    private lateinit var tpEndTime : Button
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
        tpStartTime = view.findViewById(R.id.modify_recurring_task_start_time)
        tpEndTime = view.findViewById(R.id.modify_recurring_task_end_time)

        var startDate = StructureDateTime()
        var endDate = StructureDateTime()

        startDatePicker.setText(getDateString(startDate))
        endDatePicker.setText(getDateString(endDate))
        tpStartTime.setText(getTimeString(startDate))
        tpEndTime.setText(getTimeString(endDate))

        startDatePicker.setOnClickListener {
            DatePickerDialogOpener(requireContext(),startDate,startDatePicker)
        }

        endDatePicker.setOnClickListener {
            DatePickerDialogOpener(requireContext(),endDate,endDatePicker)
        }

        tpStartTime.setOnClickListener {
            TimePickerDialogOpener(requireContext(),startDate,tpStartTime)
        }

        tpEndTime.setOnClickListener {
            TimePickerDialogOpener(requireContext(),endDate,tpEndTime)
        }

        btSave.setOnClickListener {
            if (!compareDateTime(startDate,endDate)){
                Toast.makeText(requireContext(),"Start date/time must be later than end date/time",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newMultipleTask = StructureMultipleTasks(
                -1,
                etTaskDescription.text.toString(),
                startDate,
                endDate,
                etCompleted.text.toString().toInt(),
                etTotal.text.toString().toInt()
            )

            val dbManager = DatabaseManager(requireContext())
            dbManager.insertMultipleTask(newMultipleTask)
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