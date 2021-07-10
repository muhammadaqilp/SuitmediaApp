package com.example.suitmediaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediaapp.R
import com.example.suitmediaapp.data.model.GuestItem
import com.example.suitmediaapp.databinding.ItemListEventBinding
import com.example.suitmediaapp.databinding.ItemListGuestBinding

class ListGuestAdapter(
    private val listGuest: ArrayList<GuestItem>,
    val listener: OnGuestClickCallback? = null
) : RecyclerView.Adapter<ListGuestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGuestAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_guest, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListGuestAdapter.ViewHolder, position: Int) {
        holder.bind(listGuest[position])
    }

    override fun getItemCount(): Int = listGuest.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private val binding = ItemListGuestBinding.bind(itemView)

        fun bind(dataGuest: GuestItem){
            with(itemView){
                binding.txtGuest.text = dataGuest.name

                setOnClickListener {
                    listener?.onGuestClicked(dataGuest)
                }
            }
        }
    }

    interface OnGuestClickCallback{
        fun onGuestClicked(data: GuestItem)
    }

    fun setData(list: ArrayList<GuestItem>){
        this.listGuest.clear()
        this.listGuest.addAll(list)
        notifyDataSetChanged()
    }
}