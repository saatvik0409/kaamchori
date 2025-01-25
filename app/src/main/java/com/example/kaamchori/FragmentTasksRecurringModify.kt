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
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.getYearMonthDay
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText


class FragmentTasksRecurringModify : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var thisRecurringTaskIndex : Int = 0
    private lateinit var thisRecurringTask : StructureRecurringTasks
    private lateinit var etDescription : TextInputEditText
    private lateinit var dpStartDate : DatePicker
    private lateinit var dpEndDate : DatePicker
    private lateinit var etFrequency : TextInputEditText
    private lateinit var tbStatus : ToggleButton
    private lateinit var btSave : Button

    private lateinit var etStartHour : TextInputEditText
    private lateinit var etStartMinute : TextInputEditText
    private lateinit var etStartSecond : TextInputEditText

    private lateinit var etEndHour: TextInputEditText
    private lateinit var etEndMinute: TextInputEditText
    private lateinit var etEndSecond : TextInputEditText

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
        etFrequency = view.findViewById(R.id.modify_recurring_task_frequency_edit_text)
        tbStatus = view.findViewById(R.id.modify_recurring_task_status)
        btSave = view.findViewById(R.id.modify_recurring_task_save)

        etStartHour = view.findViewById(R.id.modify_recurring_task_hour_start_edit_text)
        etStartMinute =view.findViewById(R.id.modify_recurring_task_minute_start_edit_text)
        etStartSecond = view.findViewById(R.id.modify_recurring_task_second_start_edit_text)

        etEndHour = view.findViewById(R.id.modify_recurring_task_hour_end_edit_text)
        etEndMinute =view.findViewById(R.id.modify_recurring_task_minute_end_edit_text)
        etEndSecond = view.findViewById(R.id.modify_recurring_task_second_end_edit_text)

        etDescription.setText(thisRecurringTask.taskDescription)

        val startDate = getYearMonthDay(thisRecurringTask.startDate)
        dpStartDate.updateDate(
            startDate.year,
            startDate.month,
            startDate.day
        )
        etStartHour.setText(startDate.hour.toString())
        etStartSecond.setText(startDate.second.toString())
        etStartMinute.setText(startDate.minute.toString())


        val endDate = getYearMonthDay(thisRecurringTask.endDate)
        dpEndDate.updateDate(
            endDate.year,
            endDate.month,
            endDate.day
        )
        etEndHour.setText(endDate.hour.toString())
        etEndMinute.setText(endDate.minute.toString())
        etEndSecond.setText(endDate.second.toString())

        etFrequency.setText(thisRecurringTask.frequency.toString())
        tbStatus.isChecked = thisRecurringTask.status

        btSave.setOnClickListener {
            val startDateStructure = StructureDateTime(
                dpStartDate.year,
                dpStartDate.month+1,
                dpStartDate.dayOfMonth,
                etStartHour.text.toString().toInt(),
                etStartMinute.text.toString().toInt(),
                etStartSecond.text.toString().toInt()
            )
            val startDateInstance = yearMonthDayToDate(startDateStructure)


            val endDateStructure = StructureDateTime(
                dpEndDate.year,
                dpEndDate.month+1,
                dpEndDate.dayOfMonth,
                etEndHour.text.toString().toInt(),
                etEndMinute.text.toString().toInt(),
                etEndSecond.text.toString().toInt()
            )
            val endDateInstance = yearMonthDayToDate(endDateStructure)

            val newRecurringTask = StructureRecurringTasks(
                etDescription.text.toString(),
                startDateInstance,
                endDateInstance,
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