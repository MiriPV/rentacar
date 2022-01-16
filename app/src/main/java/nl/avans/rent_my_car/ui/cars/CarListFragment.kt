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
    ) {
        // getting the recyclerview by its id
        //val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        //recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        //val data = ArrayList<CarViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        //for (i in 1..20) {
        //    data.add(CarViewModel(R.drawable.ic_baseline_view_list_24, "Item " + i))
        //}

        // This will pass the ArrayList to our Adapter
        //val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        //recyclerview.adapter = adapter

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
            data.add(CarViewModel(1, "Item " + i))
        }

        val adapter = CustomAdapter(data)

        recyclerView.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}