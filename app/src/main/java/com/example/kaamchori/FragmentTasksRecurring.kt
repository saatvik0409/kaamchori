package com.example.kaamchori

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kaamchori.adapters.AdapterRecurringTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTasksRecurring.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTasksRecurring : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rvRecurringTasks : RecyclerView
    private lateinit var adpRecurringTasks : AdapterRecurringTasks //Note that always follow this convention for naming class -> Full adapter, for variable -> adp
    private lateinit var fabNewRecurringTask : FloatingActionButton


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
        fabNewRecurringTask = view.findViewById(R.id.recurring_tasks_fab)
        adpRecurringTasks = AdapterRecurringTasks(GlobalVariables.recurringTasksList){item ->
            Log.i(TAG,"The item argument is ${item}")
            val newBundle = Bundle()
            newBundle.putInt("clickedRecurringTask",item)
            findNavController().navigate(R.id.fragmentModifyRecurringTasks, newBundle)
        }
        rvRecurringTasks.adapter = adpRecurringTasks
        rvRecurringTasks.layoutManager =LinearLayoutManager(requireContext())

        fabNewRecurringTask.setOnClickListener {
            findNavController().navigate(R.id.fragmentNewRecurringTask)
        }
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
        private val TAG = "FRAGMENT_RECURRING_TASKS"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTasksRecurring().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}