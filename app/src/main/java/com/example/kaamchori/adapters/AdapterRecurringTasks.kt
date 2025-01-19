package com.example.kaamchori.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kaamchori.R
import com.example.kaamchori.models.StructureRecurringTasks
import java.text.SimpleDateFormat

class AdapterRecurringTasks (
    private val recurringTasksList : List<StructureRecurringTasks>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<AdapterRecurringTasks.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_description : TextView= itemView.findViewById(R.id.item_recurring_tasks_title)
        val tv_start_date : TextView= itemView.findViewById(R.id.item_recurring_tasks_start)
        val tv_end_date : TextView = itemView.findViewById(R.id.item_recurring_tasks_end)
        val tv_status : TextView = itemView.findViewById(R.id.item_recurring_tasks_status)
        val tv_frequency: TextView = itemView.findViewById(R.id.item_recurring_tasks_cycle)

        fun bind(item : Int){
            itemView.setOnClickListener{
                onItemClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recurring_tasks,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recurringTasksList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask = recurringTasksList[position]
        holder.tv_description.text = currentTask.taskDescription
        holder.tv_start_date.text = SimpleDateFormat("dd/MM/yy").format(currentTask.startDate)
        holder.tv_end_date.text = SimpleDateFormat("dd/MM/yy").format(currentTask.endDate)
        holder.tv_status.text = currentTask.status.toString()
        holder.tv_frequency.text = currentTask.frequency.toString()
        holder.bind(position)
    }
}