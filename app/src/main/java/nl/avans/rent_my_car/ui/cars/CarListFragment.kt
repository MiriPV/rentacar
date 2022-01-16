package nl.avans.rent_my_car.ui.cars

import CustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.databinding.FragmentCarListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import nl.avans.rent_my_car.R


class CarListFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private var _binding: FragmentCarListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       // carViewModel =
        //    ViewModelProvider(this)[carViewModel]

        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.carList
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        //val adapter = CustomAdapter(arrayOf("mercedes", "BMW", "audi"))
        // ArrayList of class ItemsViewModel
        val data = ArrayList<CarViewModel>()

        for (i in 1..20) {
            data.add(CarViewModel(R.drawable.ic_baseline_view_list_24, "Item " + i))
        }

        val adapter = CustomAdapter(data)

        recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}