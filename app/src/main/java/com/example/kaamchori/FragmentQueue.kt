package com.example.kaamchori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.LinearProgressIndicator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.kaamchori.adapters.AdapterQueueTasks
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.generateQueue

class FragmentQueue : Fragment() {

    private lateinit var rvQueue : RecyclerView
    private lateinit var adpQueue : AdapterQueueTasks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateQueue()

        rvQueue = view.findViewById(R.id.rvQueue)

        adpQueue = AdapterQueueTasks(GlobalVariables.queueTasksList, requireContext())
        rvQueue.adapter = adpQueue

        rvQueue.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        private val TAG = "FRAGMENT_QUEUE"
    }
}