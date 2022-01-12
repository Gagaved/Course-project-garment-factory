import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.models.ClothPack

class ListViewAdapterMaterials(private val context: Context,
                               private val list: ArrayList<ClothPack>) : BaseAdapter() {

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
        val itemView = inflater.inflate(R.layout.item_roll, parent, false)
        val code = itemView.findViewById(R.id.code) as TextView
        code.text = list[position].number
        val length = itemView.findViewById(R.id.length) as TextView
        length.text = list[position].length.toString()
        val button = itemView.findViewById(R.id.button) as MaterialButton
        val input = itemView.findViewById(R.id.input) as TextInputEditText

        return itemView
    }
}