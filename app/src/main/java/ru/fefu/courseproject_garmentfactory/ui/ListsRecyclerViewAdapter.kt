package ru.fefu.courseproject_garmentfactory.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.courseproject_garmentfactory.R

class ListRecyclerViewAdapter(private val listItems: List<ItemListData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemClickListener: (Int) -> Unit = {}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
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
        private var id: Int = -1
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val code = itemView.findViewById<TextView>(R.id.code)
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val count = itemView.findViewById<TextView>(R.id.count)
        private val counttext = itemView.findViewById<TextView>(R.id.counttext)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                itemClickListener.invoke(position)
            }
        }

        fun bind(item: ItemListData) {
            id = item.id
            name.text = item.name
            code.text = item.article.toString()
            count.text = item.count.toString()
            if (item.count > 0) {
                count.visibility = View.VISIBLE
                counttext.visibility = View.VISIBLE
            }
            SetImageToViewFromURL(image).execute("http://sewing.mrfox131.software/" + item.image)
        }
    }

}