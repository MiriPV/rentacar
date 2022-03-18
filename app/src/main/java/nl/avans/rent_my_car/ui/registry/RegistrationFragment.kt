package nl.avans.rent_my_car.ui.registry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.rent_my_car.databinding.FragmentRegistrationBinding
import nl.avans.rent_my_car.model.Car
import nl.avans.rent_my_car.model.CarViewModel
import java.io.ByteArrayOutputStream


class RegistrationFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var sensorManager: SensorManager
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var lightSensor: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        pref = activity!!.getPreferences(Context.MODE_PRIVATE)
        editor = pref.edit()
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        val brandEditView: EditText = binding.brand
        val typeEditView: EditText = binding.type
        val lpEditView: EditText = binding.lp
        val seatsEditView: EditText = binding.seats
        val costEditView: EditText = binding.cost
        val userIdEditView: EditText = binding.userId
        val button: Button = binding.buttonSend
        val delete: Button = binding.buttonDelete
        val pictureButton : Button = binding.buttonTakePicture
        val resultTextView: TextView = binding.textViewResult
        val picturePreview: ImageView = binding.picturePreview

        //Check if there is a userId that was saved before
        val id: Int = pref.getInt("userId", 999)
        if (id != 999) {
            userIdEditView.setText(id.toString())
        }

        //Camera listener
        val takePhoto = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val imageBitmap = it.data?.extras?.get("data") as Bitmap
                picturePreview.setImageBitmap(imageBitmap)
            }
        }

        button.setOnClickListener {
            val model: CarViewModel by viewModels()
            //Use the values from the edittext fields, to add a car to the api
            val brand: String = brandEditView.text.toString()
            val type: String = typeEditView.text.toString()
            val lp: String = lpEditView.text.toString()
            val imageString: String?
            //Get picture from the preview
            val bm: Bitmap? = try{
                picturePreview.drawable.toBitmap()
            } catch(e: Exception) {
                null
            }

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
            val userId: Int = try {
                userIdEditView.text.toString().toInt()
            } catch (e: Exception) {
                1
            }
            //Save the userId with Shared Preferences so next time the userId is available
            editor.putInt("userId", userIdEditView.text.toString().toInt())
            editor.apply()

            if(bm != null) {
                imageString = encodeImage(bm)
                model.addCar(userId, Car(brand, type, null, userId, lp, seats, cost, imageString))
            } else {
                model.addCar(userId, Car(brand, type, null, userId, lp, seats, cost, null))
            }

            model.postResponse.observe(this) {
                resultTextView.text = model.postResponse.value
            }
        }

        //Empty all fields and delete the save user id
        delete.setOnClickListener{
            brandEditView.setText("")
            typeEditView.setText("")
            lpEditView.setText("")
            seatsEditView.setText("")
            costEditView.setText("")
            userIdEditView.setText("")
            editor.clear()
            editor.apply()
        }

        pictureButton.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhoto.launch(takePictureIntent)

        }
            return binding.root
    }

    private fun encodeImage(bm: Bitmap?): String {
        val outputStream = ByteArrayOutputStream()
        bm?.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
        val b = outputStream.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
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
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}