package nl.avans.rent_my_car.ui.cars

import nl.avans.rent_my_car.util.CustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.databinding.FragmentCarListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.model.Car
import nl.avans.rent_my_car.model.RentMyCarDAO


class CarListFragment : Fragment() {
    private var _binding: FragmentCarListBinding? = null
    private val carDAO = RentMyCarDAO()

    private val binding get() = _binding!!
    private lateinit var adapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.carList
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        adapter = CustomAdapter(carDAO.getAll())
        recyclerView.adapter = adapter

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}