package nl.avans.rent_my_car.ui.cars

import nl.avans.rent_my_car.util.CustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.databinding.FragmentCarListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import nl.avans.rent_my_car.model.CarViewModel


class CarListFragment : Fragment() {
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

        val model: CarViewModel by viewModels()
        model.response.observe(this) {
            recyclerView.adapter = CustomAdapter(model.response.value!!)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}