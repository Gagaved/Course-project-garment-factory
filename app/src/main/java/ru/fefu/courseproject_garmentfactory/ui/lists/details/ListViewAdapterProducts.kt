import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.fefu.courseproject_garmentfactory.R

class ListViewAdapterProducts(private val context: Context,
                              private val list: ArrayList<Pair<String,String>>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = inflater.inflate(R.layout.item_historychange, parent, false)
        val widthTextView = itemView.findViewById(R.id.length) as TextView
        val lengthTextView = itemView.findViewById(R.id.length) as TextView
        val fabricsListView = itemView.findViewById(R.id.length) as TextView
        val fittingsListView = itemView.findViewById(R.id.length) as TextView
        val dateView = itemView.findViewById(R.id.length) as TextView
        //lengthTextView.text = list[position].second

        return itemView
    }
}