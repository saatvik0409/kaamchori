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
import com.example.kaamchori.models.StructureDate
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.getYearMonthDay
import com.example.kaamchori.utils.yearMonthDayToDate
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTasksRecurringModify.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTasksRecurringModify : Fragment() {
    // TODO: Rename and change types of parameters
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            thisRecurringTaskIndex = it.getInt("clickedRecurringTask")
            thisRecurringTask = GlobalVariables.recurringTasksList[thisRecurringTaskIndex]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        etDescription.setText(thisRecurringTask.taskDescription)

        val startDate = getYearMonthDay(thisRecurringTask.startDate)
        dpStartDate.updateDate(
            startDate.year,
            startDate.month,
            startDate.day
        )
        val endDate = getYearMonthDay(thisRecurringTask.endDate)
        dpEndDate.updateDate(
            endDate.year,
            endDate.month,
            endDate.day
        )
        etFrequency.setText(thisRecurringTask.frequency.toString())
        tbStatus.isChecked = thisRecurringTask.status

        btSave.setOnClickListener {
            val startDateStructure = StructureDate(
                dpStartDate.year,
                dpStartDate.month+1,
                dpStartDate.dayOfMonth
            )
            val startDateInstance = yearMonthDayToDate(startDateStructure)

            val endDateStructure = StructureDate(
                dpEndDate.year,
                dpEndDate.month+1,
                dpEndDate.dayOfMonth
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
                    .setPopUpTo(R.id.fragmentModifyRecurringTasks, true) // Removes currentFragment from back stack
                    .build()
            )
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentModifyRecurringTask.
         */
        // TODO: Rename and change types and number of parameters
        private var TAG = "FRAGMENT_MODIFY_RECURRING_TASKS"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTasksRecurringModify().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}