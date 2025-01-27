package com.example.kaamchori.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kaamchori.R
import com.example.kaamchori.models.StructureGenericTask
import com.example.kaamchori.utils.getDateString
import com.example.kaamchori.utils.getTimeString
import com.example.kaamchori.utils.yearMonthDayToDate
import org.w3c.dom.Text
import java.util.Calendar
import java.util.Date

class AdapterQueueTasks (
    private val queueList : MutableList<StructureGenericTask>,
    private val context : Context
): RecyclerView.Adapter<AdapterQueueTasks.MyViewHolder> (){
    inner class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvTaskDesc : TextView = itemView.findViewById(R.id.tvTaskDesc)
        val tvDeadlineDate: TextView = itemView.findViewById(R.id.tvDeadlineDate)
        val tvDeadlineTime: TextView = itemView.findViewById(R.id.tvDeadlineTime)
        val progressBar: ProgressBar = itemView.findViewById(R.id.pbTime)
        val cardQueueTask : CardView = itemView.findViewById(R.id.cardQueueTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_queue_task,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return queueList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val thisTask : StructureGenericTask = queueList[position]
        holder.tvDeadlineDate.text = getDateString(thisTask.taskDeadline)
        holder.tvDeadlineTime.text = getTimeString(thisTask.taskDeadline)
        holder.tvTaskDesc.text = thisTask.taskTitle

        val startTimeInst : Long = yearMonthDayToDate(thisTask.startDateTime).time
        val endTimeInst : Long = yearMonthDayToDate(thisTask.taskDeadline).time
        val currentTime : Long = System.currentTimeMillis()
        val progress : Int =  ((currentTime-startTimeInst)*100/(endTimeInst-startTimeInst)).toInt()
        if (progress <= 100) holder.progressBar.progress = progress
        else {
            holder.progressBar.progress = 100
            holder.cardQueueTask.setBackgroundColor(
                ContextCompat.getColor(context,R.color.light_red)
            )
            holder.cardQueueTask.radius = 15.0f

        }
    }

    companion object {
        private val TAG = "ADAPTER_QUEUE_TASKS"
    }
}