package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kaamchori.adapters.AdapterRecurringTasks
import com.example.kaamchori.models.StructureRecurringTasks
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Recurring_Tasks.newInstance] factory method to
 * create an instance of this fragment.
 */
class Recurring_Tasks : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rvRecurringTasks : RecyclerView
    private lateinit var adpRecurringTasks : AdapterRecurringTasks //Note that always follow this convention for naming class -> Full adapter, for variable -> adp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recurring_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        requireActivity().supportFragmentManager.popBackStack()
        rvRecurringTasks = view.findViewById(R.id.recurring_tasks_rv)
        adpRecurringTasks = AdapterRecurringTasks(generateRecurringTasks())
        rvRecurringTasks.adapter = adpRecurringTasks
        rvRecurringTasks.layoutManager =LinearLayoutManager(requireContext())
    }


    fun generateRecurringTasks(): List<StructureRecurringTasks> {
        return listOf(
            StructureRecurringTasks(
                taskDescription = "Water the plants",
                startDate = Date(), // Current date
                endDate = Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000), // 7 days from now
                frequency = 24, // Every 24 hours
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Take out the trash",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000), // 30 days from now
                frequency = 168, // Weekly (168 hours)
                status = false
            ),
            StructureRecurringTasks(
                taskDescription = "Go jogging",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000), // 14 days from now
                frequency = 48, // Every 48 hours
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Clean the house",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 21 * 24 * 60 * 60 * 1000), // 21 days from now
                frequency = 168, // Weekly
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Pay electricity bill",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 60 * 24 * 60 * 60 * 1000), // 60 days from now
                frequency = 720, // Monthly (approx 720 hours)
                status = false
            ),
            StructureRecurringTasks(
                taskDescription = "Check email",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000), // 7 days from now
                frequency = 12, // Every 12 hours
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Grocery shopping",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 28 * 24 * 60 * 60 * 1000), // 28 days from now
                frequency = 168, // Weekly
                status = true
            ),
            StructureRecurringTasks(
                taskDescription = "Visit dentist",
                startDate = Date(),
                endDate = Date(System.currentTimeMillis() + 90 * 24 * 60 * 60 * 1000), // 90 days from now
                frequency = 2160, // Quarterly (approx 2160 hours)
                status = false
            )
        )
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Recurring_Tasks.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Recurring_Tasks().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}