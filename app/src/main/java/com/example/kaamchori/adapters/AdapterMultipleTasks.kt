package com.example.kaamchori.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.compose.runtime.compositionLocalOf
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kaamchori.R
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.utils.getDateString
import com.example.kaamchori.utils.getTimeString
import java.text.SimpleDateFormat

class AdapterMultipleTasks (
    private val tasksList : MutableList<StructureMultipleTasks>,
    private val onItemClick : (Int) -> Unit
) : RecyclerView.Adapter<AdapterMultipleTasks.MyViewHolder> () {

    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val tvDesc : TextView = itemView.findViewById(R.id.item_recurring_tasks_title)
        val tvStartDate : TextView = itemView.findViewById(R.id.item_recurring_tasks_start)
        val tvEndDate : TextView = itemView.findViewById(R.id.item_recurring_tasks_end)
        val tvStatus : TextView = itemView.findViewById(R.id.item_recurring_tasks_status)
        val tvFrequency : TextView = itemView.findViewById(R.id.item_recurring_tasks_cycle)
        val tv_start_time : TextView = itemView.findViewById(R.id.item_recurring_tasks_start_time)
        val tv_end_time : TextView = itemView.findViewById(R.id.item_recurring_tasks_end_time)

        fun bind (position: Int){
            itemView.setOnClickListener {
                onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recurring_tasks,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask= tasksList[position]
        holder.tvFrequency.visibility = View.GONE
        holder.tvDesc.text =currentTask.taskDescription
        holder.tvStartDate.text = getDateString(currentTask.startDate)
        holder.tvEndDate.text = getDateString(currentTask.endDate)
        holder.tvStatus.text = "${currentTask.status}/${currentTask.totalQty}"
        holder.tv_start_time.text = getTimeString(currentTask.startDate)
        holder.tv_end_time.text = getTimeString(currentTask.endDate)
        holder.bind(position)
    }
}