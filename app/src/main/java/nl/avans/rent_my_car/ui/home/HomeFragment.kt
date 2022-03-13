package nl.avans.rent_my_car.ui.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), SensorEventListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var lightModeDarkMode: ImageView
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        lightModeDarkMode = binding.temperatureSensor
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 40) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            lightModeDarkMode.setImageResource(R.drawable.ic_baseline_wb_sunny_24)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            lightModeDarkMode.setImageResource(R.drawable.ic_baseline_nights_stay_24)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}