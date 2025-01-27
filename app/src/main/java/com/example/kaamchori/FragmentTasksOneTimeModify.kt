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
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.DatePickerDialogOpener
import com.example.kaamchori.utils.TimePickerDialogOpener
import com.example.kaamchori.utils.compareDateTime
import com.example.kaamchori.utils.getDateString
import com.example.kaamchori.utils.getTimeString
import com.example.kaamchori.utils.getYearMonthDay
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText

class FragmentTasksOneTimeModify : Fragment() {

    private var indexOneTimeTask : Int = 0
    private lateinit var thisOneTimeTask : StructureOneTimeTasks

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
        arguments?.let {it ->
            indexOneTimeTask = it.getInt("oneTimeTaskIndex")
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

        thisOneTimeTask = GlobalVariables.oneTimeTasksList[indexOneTimeTask]

        etDescription = view.findViewById(R.id.modify_recurring_task_title_edit_text)
        dpStartDate = view.findViewById(R.id.modify_recurring_task_start_date)
        dpEndDate = view.findViewById(R.id.modify_recurring_task_end_date)
        tpStartTime = view.findViewById(R.id.modify_recurring_task_start_time)
        tpEndTime = view.findViewById(R.id.modify_recurring_task_end_time)
        etFrequency = view.findViewById(R.id.modify_recurring_task_frequency_edit_text)
        tbStatus = view.findViewById(R.id.modify_recurring_task_status)
        btSave = view.findViewById(R.id.modify_recurring_task_save)

        etFrequency.visibility = View.GONE

        etDescription.setText(thisOneTimeTask.taskDescription)
        tbStatus.isChecked = thisOneTimeTask.status

        val startDate = thisOneTimeTask.startDate.copy()
        dpStartDate.setText(getDateString(startDate))
        tpStartTime.setText(getTimeString(startDate))

        val endDate = thisOneTimeTask.endDate.copy()
        dpEndDate.setText(getDateString(endDate))
        tpEndTime.setText(getTimeString(endDate))


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
                Toast.makeText(requireContext(),"Start date/time must be later than end date/time",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newOneTimeTask = StructureOneTimeTasks(
                etDescription.text.toString(),
                startDate,
                endDate,
                tbStatus.isChecked
            )

            GlobalVariables.oneTimeTasksList[indexOneTimeTask] = newOneTimeTask
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
        private val TAG = "FRAGMENT_ONE_TIME_TASKS_MODIFY"
    }
}