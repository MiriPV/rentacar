package nl.avans.rent_my_car.ui.registry

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.hardware.camera2.CameraDevice
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.databinding.FragmentRegistrationBinding
import nl.avans.rent_my_car.model.Car
import nl.avans.rent_my_car.model.CarViewModel
import java.io.ByteArrayOutputStream

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val brandEditView: EditText = binding.brand
        val typeEditView: EditText = binding.type
        val lpEditView: EditText = binding.lp
        val seatsEditView: EditText = binding.seats
        val costEditView: EditText = binding.cost
        val userIdEditView: EditText = binding.userId
        val button: Button = binding.buttonSend
        val pictureButton : Button = binding.buttonTakePicture
        val resultTextView: TextView = binding.textViewResult
        val picturePreview: ImageView = binding.picturePreview

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
            var imageString: String?
            //Get picture from the preview
            val bm: Bitmap? = try{
                picturePreview.drawable.toBitmap()
            } catch(e: Exception) {
                null
            }
            if(bm != null) {
                imageString = encodeImage(bm)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}