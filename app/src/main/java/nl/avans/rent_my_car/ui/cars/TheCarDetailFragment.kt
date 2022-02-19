package nl.avans.rent_my_car.ui.cars

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.databinding.FragmentTheCarDetailBinding
import nl.avans.rent_my_car.model.CarViewModel
import nl.avans.rent_my_car.util.CustomAdapter

private const val ARG_LP = "licencePlate"
private const val ARG_ID = "id"
private const val ARG_BRAND = "brand"
private const val ARG_TYPE = "type"
private const val ARG_SEATS = "seats"
private const val ARG_COST = "cost"
private const val ARG_PICTURE = "picture"

class TheCarDetailFragment : Fragment() {
    private var _binding: FragmentTheCarDetailBinding? = null
    private val binding get() = _binding!!

    private var paramLP: String? = null
    private var paramId: Long? = null
    private var paramBrand: String? = null
    private var paramType: String? = null
    private var paramSeats: String? = null
    private var paramCost: String? = null
    private var paramPicture: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramLP = it.getString(ARG_LP)
            paramId = it.getLong(ARG_ID)
            paramBrand = it.getString(ARG_BRAND)
            paramType = it.getString(ARG_TYPE)
            paramSeats = it.getString(ARG_SEATS)
            paramCost = it.getString(ARG_COST)
            paramPicture = it.getString(ARG_PICTURE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTheCarDetailBinding.inflate(inflater, container, false)
        val imageView : ImageView = binding.ivCar
        val deleteButton: Button = binding.btnDelete
        val textViewBrand: TextView = binding.tvBrand
        val textViewType: TextView = binding.tvType
        val textViewSeats: TextView = binding.tvSeats
        val textViewCost: TextView = binding.tvCost

        //If the car has a picture, use it. Else use the default image.
        if(paramPicture != "" && paramPicture != null) {
            val pictureBitmap: Bitmap? = paramPicture?.let { decodeImage(it) }
            imageView.setImageBitmap(pictureBitmap)
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_directions_car_24)
        }

        textViewBrand.text = paramBrand
        textViewType.text = paramType
        textViewSeats.text = paramSeats
        textViewCost.text = paramCost

        deleteButton.setOnClickListener {
            val model: CarViewModel by viewModels()
            paramId?.let { id -> model.deleteCar(id) }
            model.deleteResponse.observe(this) {
                if(it.equals("car deleted successfully")) {
                    val string: String? = context?.getString(R.string.toast_message)
                    Toast.makeText(this.requireContext(),string,Toast.LENGTH_LONG).show()
                    val navController = Navigation.findNavController(this.requireView())
                    navController.navigate(R.id.car_list_fragment)
                } else {
                    val string: String? = context?.getString(R.string.toast_message_error)
                    Toast.makeText(this.requireContext(),string,Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    private fun decodeImage(picture: String): Bitmap? {
        val decodedString: ByteArray = Base64.decode(picture, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}