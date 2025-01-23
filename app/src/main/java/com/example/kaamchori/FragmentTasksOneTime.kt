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
import com.example.kaamchori.adapters.AdapterOneTimeTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentTasksOneTime : Fragment() {

    private lateinit var tvTitle : TextView
    private lateinit var rvTasks : RecyclerView
    private lateinit var fabNewTasks : FloatingActionButton
    private lateinit var adpOneTimeTasks : AdapterOneTimeTasks
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
        fabNewTasks = view.findViewById(R.id.recurring_tasks_fab)

        tvTitle.text = "One Time Tasks"

        adpOneTimeTasks = AdapterOneTimeTasks(GlobalVariables.oneTimeTasksList) {position ->
            val bundle = Bundle()
            bundle.putInt("oneTimeTaskIndex",position)
            findNavController().navigate(R.id.oneTimeTasksModifyFragment,bundle)
        }

        rvTasks.adapter = adpOneTimeTasks
        rvTasks.layoutManager = LinearLayoutManager(requireContext())

    }
    companion object {
        private val TAG = "FRAGMENT_ONE_TIME_TASKS"
    }
}