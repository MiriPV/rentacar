package nl.avans.rent_my_car.model

import android.content.Context
import android.widget.Toast
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.rent_my_car.network.CarApi

private const val TAG = "CarViewModel"

class CarViewModel : ViewModel() {
    private val _response: MutableLiveData<List<Car>> = MutableLiveData()
    val response: LiveData<List<Car>>
    get() = _response

    init {
        getAllCars()
    }

    fun getAllCars() {
        viewModelScope.launch {
            try {
                _response.value = CarApi.carService.getCars().toList()
            } catch (e: Exception) {
                print("HELP: " + e.message)
            }
        }
    }


}