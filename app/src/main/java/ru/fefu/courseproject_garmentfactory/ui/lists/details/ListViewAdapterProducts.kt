import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.models.Product

class ListViewAdapterProducts(private val context: Context,
                              private val list: List<Product>) : BaseAdapter() {

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
        val width= itemView.findViewById(R.id.width) as TextView
        width.text = list[position].width.toString()
        val length = itemView.findViewById(R.id.length) as TextView
        length.text = list[position].length.toString()
        var accessoriesList = ""
        for(i in list[position].accessories){
            accessoriesList+=i.name+"\n"
        }
        var clothList = ""
        for(i in list[position].clothes){
            clothList+=i.name+"\n"
        }
        val fabrics = itemView.findViewById(R.id.fablricslist) as TextView
        fabrics.text = clothList
        val fittings = itemView.findViewById(R.id.fittingslist) as TextView
        fittings.text = accessoriesList
        //val dateView = itemView.findViewById(R.id.length) as TextView
        //lengthTextView.text = list[position].second

        return itemView
    }
}