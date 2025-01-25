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
        holder.tvDesc.text =currentTask.taskDescription
        holder.tvStartDate.text = SimpleDateFormat("dd/MM/yy").format(currentTask.startDate)
        holder.tvEndDate.text = SimpleDateFormat("dd/MM/yy").format(currentTask.endDate)
        holder.tvStatus.text = "${currentTask.status}/${currentTask.totalQty}"
        holder.bind(position)
    }
}