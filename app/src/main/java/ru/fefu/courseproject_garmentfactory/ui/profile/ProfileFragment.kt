package ru.fefu.courseproject_garmentfactory.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.MainActivity
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Profile
import ru.fefu.courseproject_garmentfactory.databinding.FragmentProfileBinding
import ru.fefu.courseproject_garmentfactory.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.ProfileFullName
//        profileViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        getAndFillProfile()
        val buttonExit = binding.ProfileButtonExit
        buttonExit.setOnClickListener {
            destroyToken()
            goToLogin()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun destroyToken() {
        val editor = App.getSharedPref.edit()
        editor.remove(App.APP_PREFERENCES_TOKEN)
        editor.apply()
    }

    private fun fillProfile(profile: Profile) {
        val fullName = binding.ProfileFullName
        val role = binding.ProfileAccountType
        fullName.text = profile.name.replace(' ','\n')
        role.text = when (profile.role) {
            1 -> "заказчик"
            2 -> "менеджер"
            3 -> "кладовщик"
            4 -> "швея"
            5 -> "директор"
            else -> "ошбика при загрузке"
        }
    }

    private fun getAndFillProfile() {
        App.getSharedPref.getString(App.APP_PREFERENCES_TOKEN, "")?.let {
            App.getApi.getProfile(it).enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            Log.e("success get profile", "empty body")
                        }
                        else {
                            fillProfile(body)
                        }
                    }
                    else {
                        destroyToken()
                        //TODO go to login screen
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.e("get profile", t.message.toString())
                }
            })
        }
    }

    private fun goToLogin() {
        val intent = Intent(activity?.baseContext, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}