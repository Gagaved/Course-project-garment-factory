import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.*
import ru.fefu.courseproject_garmentfactory.databinding.ActivityLoginBinding

class ListViewAdapterMaterials(private val context: Context,
                               private val list: List<ClothPack>,
                               private val article: Int) : BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        val json: JsonElement
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
        code.text = list[position].number.toString()
        val length = itemView.findViewById(R.id.length) as TextView
        length.text = list[position].length.toString()
        val button = itemView.findViewById(R.id.button) as MaterialButton
        val input = itemView.findViewById(R.id.input) as TextInputEditText
        button.setOnClickListener{
            App.getApi.clothDecommission(App.getToken(), article, list[position].number,(input.text.toString()+"F").toFloat()).enqueue(object: Callback<ClothDecommissionResponse> {
                override fun onFailure(call: Call<ClothDecommissionResponse>, t: Throwable) {
                    Log.i("fail clothDecommission", t.message.toString())
                }

                override fun onResponse(call: Call<ClothDecommissionResponse>, response: Response<ClothDecommissionResponse>) {
                    val textError: TextView
                    if (response.isSuccessful) {
                        val body = response.body()
                        length.text = (length.text.toString().toFloat() - (input.text.toString()+"F").toFloat()).toString()
                        Log.i("succ. clothDecommission",input.text.toString())
                    } else {
                        when (response.code()) {
                            401 -> {
                                //textError.text = "Неправильная связка логин-пароль, проверьте правильность введённых данных"
                            }
                            else -> {
                                //textError.text = response.message()
                            }
                        }
                    }
                }
            })
        }
        return itemView
    }
}