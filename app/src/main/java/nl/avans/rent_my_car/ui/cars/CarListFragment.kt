package nl.avans.rent_my_car.ui.cars

import nl.avans.rent_my_car.CustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.databinding.FragmentCarListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import nl.avans.rent_my_car.MainActivity
import nl.avans.rent_my_car.R


class CarListFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private var _binding: FragmentCarListBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.carList
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val data = ArrayList<CarViewModel>()

        for (i in 1..20) {
            data.add(CarViewModel(R.drawable.ic_baseline_directions_car_24, "Item " ))
        }

        adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}