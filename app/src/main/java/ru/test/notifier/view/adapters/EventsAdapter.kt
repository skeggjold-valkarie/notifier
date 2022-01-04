package ru.test.notifier.view.adapters

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

class EventsAdapter(context: Context): RecyclerView.Adapter<ViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.textView?.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int = 0

    fun add(position: Int, item: String) = items.add(position, item)

    fun removeAt(position: Int): String = items.removeAt(position)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<String>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        val foreground: LinearLayout = itemView.findViewById(R.id.foreground)
        val background: LinearLayout = itemView.findViewById(R.id.background)
        val textView: TextView = itemView.findViewById(R.id.event_header)
    }

}