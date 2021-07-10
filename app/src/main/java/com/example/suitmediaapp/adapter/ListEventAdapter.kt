package com.example.suitmediaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediaapp.R
import com.example.suitmediaapp.data.model.Event
import com.example.suitmediaapp.databinding.ItemListEventBinding

class ListEventAdapter(
    private val listEvent: ArrayList<Event>,
    val listener: OnItemClickCallback? = null
): RecyclerView.Adapter<ListEventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int = listEvent.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemListEventBinding.bind(itemView)

        fun bind(dataEvent: Event){
            with(itemView){
                Glide.with(this)
                    .load(dataEvent.image)
                    .into(binding.imgItemPhoto)

                binding.tvEventName.text = dataEvent.name
                binding.tvEventDate.text = dataEvent.date

                setOnClickListener {
                    listener?.onItemClicked(dataEvent)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Event)
    }
}