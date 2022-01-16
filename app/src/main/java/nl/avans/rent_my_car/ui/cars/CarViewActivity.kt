package nl.avans.rent_my_car.ui.cars;
import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import nl.avans.rent_my_car.R

class CarViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_car_list)


    }

}