package ru.fefu.courseproject_garmentfactory.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.courseproject_garmentfactory.R

class OrdersRecyclerViewAdapter(private val listItems: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var ItemClickListener: (Int) -> Unit = {}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(listItems[position] as OrderListData)
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int = listItems.size;

    fun setItemClickListener(listener: (Int) -> Unit) {
        ItemClickListener = listener
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var id: Int = -1
        private val customer = itemView.findViewById<TextView>(R.id.customer)
        private val code = itemView.findViewById<TextView>(R.id.code)
        private val status = itemView.findViewById<TextView>(R.id.status)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                ItemClickListener.invoke(position)
            }
        }
        fun getId(): Int{
            return id
        }
        fun bind(item: OrderListData) {
            id = item.id
            code.text = item.code
            customer.text = item.customer
            status.text = item.status
        }
    }
}