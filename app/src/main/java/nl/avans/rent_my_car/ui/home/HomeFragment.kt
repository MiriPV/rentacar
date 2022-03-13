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
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), SensorEventListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var temperatureTextView: TextView
    private lateinit var temperatureButton: Button
    private lateinit var sensorManager: SensorManager
    private var temperature: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        temperatureTextView = binding.textTemperature
        temperatureButton = binding.temperatureSensor
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)

        temperatureButton.setOnClickListener() {
            temperatureTextView.text = temperature?.power.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {
            temperatureTextView.text = p0.values[0].toString()
        }
        val string: String = "HALLO"
        Toast.makeText(this.requireContext(),string, Toast.LENGTH_LONG).show()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        val string: String? = context?.getString(R.string.toast_message_accuracy)
        Toast.makeText(this.requireContext(),string, Toast.LENGTH_LONG).show()
    }
}