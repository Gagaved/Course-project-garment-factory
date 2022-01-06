import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.fefu.courseproject_garmentfactory.R

class ListViewAdapterMaterials(private val context: Context,
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
        val codeTextView = itemView.findViewById(R.id.code) as TextView
        val lengthTextView = itemView.findViewById(R.id.length) as TextView
        val button = itemView.findViewById(R.id.buttonContinue) as MaterialButton
        val inputText = itemView.findViewById(R.id.input) as TextInputEditText
        //lengthTextView.text = list[position].second
        return itemView
    }
}