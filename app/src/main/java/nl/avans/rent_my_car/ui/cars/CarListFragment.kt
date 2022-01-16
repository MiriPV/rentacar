package nl.avans.rent_my_car.ui.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.databinding.FragmentCarListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import nl.avans.rent_my_car.CustomAdapter


class CarListFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private var _binding: FragmentCarListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carViewModel =
            ViewModelProvider(this)[CarViewModel::class.java]

        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.carList
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = CustomAdapter(arrayOf("mercedes", "BMW", "audi"))

        recyclerView.adapter = adapter

        //carViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        //})
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}