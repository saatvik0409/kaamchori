package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.compose.material3.DatePickerDialog
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.DatePickerDialogOpener
import com.example.kaamchori.utils.TimePickerDialogOpener
import com.example.kaamchori.utils.compareDateTime
import com.example.kaamchori.utils.getDateString
import com.example.kaamchori.utils.getTimeString
import com.example.kaamchori.utils.getYearMonthDay
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText


class FragmentTasksRecurringModify : Fragment() {


    private var thisRecurringTaskIndex : Int = 0
    private lateinit var thisRecurringTask : StructureRecurringTasks
    private lateinit var etDescription : TextInputEditText
    private lateinit var dpStartDate : Button
    private lateinit var dpEndDate : Button
    private lateinit var tpStartTime : Button
    private lateinit var tpEndTime : Button
    private lateinit var etFrequency : TextInputEditText
    private lateinit var tbStatus : ToggleButton
    private lateinit var btSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            thisRecurringTaskIndex = it.getInt("clickedRecurringTask")
            thisRecurringTask = GlobalVariables.recurringTasksList[thisRecurringTaskIndex]
        }
    }

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
        tpStartTime = view.findViewById(R.id.modify_recurring_task_start_time)
        tpEndTime = view.findViewById(R.id.modify_recurring_task_end_time)
        etFrequency = view.findViewById(R.id.modify_recurring_task_frequency_edit_text)
        tbStatus = view.findViewById(R.id.modify_recurring_task_status)
        btSave = view.findViewById(R.id.modify_recurring_task_save)


        etDescription.setText(thisRecurringTask.taskDescription)

        val startDate = thisRecurringTask.startDate.copy()
        dpStartDate.setText(getDateString(startDate))
        tpStartTime.setText(getTimeString(startDate))

        val endDate = thisRecurringTask.endDate.copy()
        dpEndDate.setText(getDateString(endDate))
        tpEndTime.setText(getTimeString(endDate))

        etFrequency.setText(thisRecurringTask.frequency.toString())
        tbStatus.isChecked = thisRecurringTask.status

        dpStartDate.setOnClickListener {
            DatePickerDialogOpener(requireContext(),startDate,dpStartDate)
        }

        dpEndDate.setOnClickListener {
            DatePickerDialogOpener(requireContext(),endDate,dpEndDate)
        }

        tpStartTime.setOnClickListener {
            TimePickerDialogOpener(requireContext(),startDate,tpStartTime)
        }

        tpEndTime.setOnClickListener {
            TimePickerDialogOpener(requireContext(),endDate,tpEndTime)
        }





        btSave.setOnClickListener {

            if (!compareDateTime(startDate,endDate)){
                Toast.makeText(requireContext(),"Start date/time must be later than end date/time",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newRecurringTask = StructureRecurringTasks(
                etDescription.text.toString(),
                startDate,
                endDate,
                etFrequency.text.toString().toInt(),
                tbStatus.isChecked
            )

            GlobalVariables.recurringTasksList[thisRecurringTaskIndex] = newRecurringTask
            findNavController().navigate(
                R.id.recurringTasksFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.recurringTasksFragment, true) // Removes currentFragment from back stack
                    .build()
            )
        }
    }


    companion object {
        private var TAG = "FRAGMENT_MODIFY_RECURRING_TASKS"
    }


}