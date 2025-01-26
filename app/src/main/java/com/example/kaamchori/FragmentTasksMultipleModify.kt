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
import com.example.kaamchori.utils.DatePickerDialogOpener
import com.example.kaamchori.utils.TimePickerDialogOpener
import com.example.kaamchori.utils.getDateString
import com.example.kaamchori.utils.getTimeString
import com.example.kaamchori.utils.getYearMonthDay
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText


class FragmentTasksMultipleModify : Fragment() {

    private var indexMultipleTask : Int = 0
    private lateinit var thisMultipleTask : StructureMultipleTasks

    private lateinit var etTaskDescription: TextInputEditText
    private lateinit var etCompleted : TextInputEditText
    private lateinit var etTotal : TextInputEditText
    private lateinit var startDatePicker: Button
    private lateinit var endDatePicker: Button
    private lateinit var tpStartTime : Button
    private lateinit var tpEndTime : Button
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
        tpStartTime = view.findViewById(R.id.modify_recurring_task_start_time)
        tpEndTime = view.findViewById(R.id.modify_recurring_task_end_time)

        thisMultipleTask = GlobalVariables.multipleTasksList[indexMultipleTask]

        etTaskDescription.setText(thisMultipleTask.taskDescription)
        val startDate = thisMultipleTask.startDate
        val endDate = thisMultipleTask.endDate

        startDatePicker.setText(getDateString(startDate))
        endDatePicker.setText(getDateString(endDate))
        tpStartTime.setText(getTimeString(startDate))
        tpEndTime.setText(getTimeString(endDate))

        etCompleted.setText(thisMultipleTask.status.toString())
        etTotal.setText(thisMultipleTask.totalQty.toString())


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

            val newMultipleTask = StructureMultipleTasks(
                etTaskDescription.text.toString(),
                startDate,
                endDate,
                etTotal.text.toString().toInt(),
                etCompleted.text.toString().toInt()
            )
            GlobalVariables.multipleTasksList[indexMultipleTask] = newMultipleTask
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
        private val TAG = "FRAGMENT_TASKS_MULTIPLE_MODIFY"
    }
}