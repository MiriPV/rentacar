package nl.avans.rent_my_car.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel()  {
    private val _text = MutableLiveData<String>().apply {
        value = "Profile Settings"
    }
    val text: LiveData<String> = _text
}