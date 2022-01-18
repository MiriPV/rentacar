package nl.avans.rent_my_car.ui.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.databinding.FragmentTheCarDetailBinding

private const val ARG_LP = "licencePlate"
private const val ARG_BRAND = "brand"
private const val ARG_TYPE = "type"
private const val ARG_SEATS = "seats"
private const val ARG_COST = "cost"

class TheCarDetailFragment : Fragment() {
    private var _binding: FragmentTheCarDetailBinding? = null
    private val binding get() = _binding!!

    private var paramLP: String? = null
    private var paramBrand: String? = null
    private var paramType: String? = null
    private var paramSeats: String? = null
    private var paramCost: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramLP = it.getString(ARG_LP)
            paramBrand = it.getString(ARG_BRAND)
            paramType = it.getString(ARG_TYPE)
            paramSeats = it.getString(ARG_SEATS)
            paramCost = it.getString(ARG_COST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTheCarDetailBinding.inflate(inflater, container, false)
        val imageView : ImageView = binding.ivCar
        val textViewBrand: TextView = binding.tvBrand
        val textViewType: TextView = binding.tvType
        val textViewSeats: TextView = binding.tvSeats
        val textViewCost: TextView = binding.tvCost

        imageView.setImageResource(R.drawable.ic_baseline_directions_car_24)
        textViewBrand.text = paramBrand
        textViewType.text = paramType
        textViewSeats.text = paramSeats
        textViewCost.text = paramCost
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}