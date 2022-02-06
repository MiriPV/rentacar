package nl.avans.rent_my_car.ui.registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.rent_my_car.databinding.FragmentRegistrationBinding
import nl.avans.rent_my_car.model.Car
import nl.avans.rent_my_car.model.CarViewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val brandEditView: EditText = binding.brand
        val typeEditView: EditText = binding.type
        val lpEditView: EditText = binding.lp
        val seatsEditView: EditText = binding.seats
        val costEditView: EditText = binding.cost
        val button: Button = binding.buttonSend
        val resultTextView: TextView = binding.textViewResult

        button.setOnClickListener {
            val model: CarViewModel by viewModels()
            //Use the values from the edittext fields, to add a car to the api
            val brand: String = brandEditView.text.toString()
            val type: String = typeEditView.text.toString()
            val lp: String = lpEditView.text.toString()

            val seats: Int = try {
                seatsEditView.text.toString().toInt()
            } catch (e: Exception) {
                0
            }
            val cost: Double = try {
                costEditView.text.toString().toDouble()
            } catch (e: Exception) {
                0.00
            }
            model.addCar(Car(brand, type, 20, lp, seats, cost))
            model.postResponse.observe(this) {
                resultTextView.text = model.postResponse.value + Car(brand, type, 20, lp, seats, cost)
            }
        }
            return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}