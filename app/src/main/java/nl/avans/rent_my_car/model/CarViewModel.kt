package nl.avans.rent_my_car.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.rent_my_car.network.CarApi
import retrofit2.http.Body

private const val TAG = "CarViewModel"

class CarViewModel : ViewModel() {
    private val _response: MutableLiveData<List<Car>> = MutableLiveData()
    val response: LiveData<List<Car>>
    get() = _response

    private val _postResponse: MutableLiveData<String> = MutableLiveData()
    val postResponse : LiveData<String>
    get() = _postResponse

    init {
        getAllCars()
    }

    private fun getAllCars() {
        viewModelScope.launch {
            try {
                _response.value = CarApi.carService.getCars().toList()
            } catch (e: Exception) {
                print("Error: " + e.message)
            }
        }
    }

    fun addCar(@Body car: Car) {
        viewModelScope.launch {
            try {
                CarApi.carService.postCar(car)
                _postResponse.value = "car: $car posted successfully"
            } catch (e: Exception) {
                _postResponse.value = "car not posted. " + e.message.toString()
            }
        }
    }


}