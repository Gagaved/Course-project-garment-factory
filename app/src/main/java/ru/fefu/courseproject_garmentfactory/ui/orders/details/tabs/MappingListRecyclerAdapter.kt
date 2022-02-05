package ru.fefu.courseproject_garmentfactory.ui.orders.details.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.models.Mapping

class MappingListRecyclerAdapter(private val listItems: MutableList<Mapping>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemClickListener: (Int) -> Unit = {}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mapping, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(listItems[position])
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int = listItems.size

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var article = itemView.findViewById<TextView>(R.id.article)
        private var batch = itemView.findViewById<TextView>(R.id.batch)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                itemClickListener.invoke(position)
            }
        }

        fun bind(item: Mapping) {
            article.text = item.article.toString()
            batch.text = item.batch_number.toString()
        }
    }
}