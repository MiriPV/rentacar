package nl.avans.rent_my_car.ui.cars

import nl.avans.rent_my_car.CustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.databinding.FragmentCarListBinding
import androidx.recyclerview.widget.LinearLayoutManager
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
            data.add(CarViewModel(1, "Item " ))
        }

        adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = requireView()!!.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        //recyclerview.layoutManager = LinearLayoutManager(this.context)


        // This will pass the ArrayList to our Adapter
        //val adapter = nl.avans.rent_my_car.CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        //recyclerview.adapter = adapter

        // carViewModel =
        //    ViewModelProvider(this)[carViewModel]

        //_binding = FragmentCarListBinding.inflate(inflater, container, false)
        //val root: View = binding.root
        //val recyclerView: RecyclerView = binding.carList
        //recyclerview.layoutManager = LinearLayoutManager(this.context)

        val data = ArrayList<CarViewModel>()

        for (i in 1..20) {
            data.add(CarViewModel(1, "Item " + i))
        }

        val adapter = CustomAdapter(data)

        //recyclerview.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}