package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kaamchori.adapters.AdapterMultipleTasks
import com.example.kaamchori.databinding.LayoutMainActivityBinding
import com.example.kaamchori.singletonClass.GlobalVariables
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentTasksMultiple : Fragment() {

    private lateinit var tvTitle : TextView
    private lateinit var rvTasks : RecyclerView
    private lateinit var fabNewTask : FloatingActionButton
    private lateinit var adpMultipleTasks : AdapterMultipleTasks


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recurring_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle = view.findViewById(R.id.recurring_tasks_text_title)
        rvTasks = view.findViewById(R.id.recurring_tasks_rv)
        fabNewTask = view.findViewById(R.id.recurring_tasks_fab)

        tvTitle.text = "Multiple Tasks"
        adpMultipleTasks = AdapterMultipleTasks(GlobalVariables.multipleTasksList){position ->
            val bundle = Bundle()
            bundle.putInt("indexMultipleTask",position)
            findNavController().navigate(R.id.multipleTasksFragmentModify,bundle)
        }

        rvTasks.adapter = adpMultipleTasks
        rvTasks.layoutManager = LinearLayoutManager(requireContext())

    }

    companion object {
        private val TAG = "FRAGMENT_TASKS_MULTIPLE"
    }
}