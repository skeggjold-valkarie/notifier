package ru.test.notifier.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.test.notifier.R

class EventsAdapter<T>: RecyclerView.Adapter<ViewHolder>() {

    private var items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.textView?.text = "text"//items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int = 0

    fun add(position: Int, item: T) = items.add(position, item).also {
        notifyItemInserted(position)
    }

    fun removeAt(position: Int): T = items.removeAt(position).also {
        notifyItemRemoved(position)
    }

    fun get(position: Int): T = items[position]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<T>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        val foreground: LinearLayout = itemView.findViewById(R.id.foreground)
        val background: LinearLayout = itemView.findViewById(R.id.background)
        val textView: TextView = itemView.findViewById(R.id.event_header)
    }

}