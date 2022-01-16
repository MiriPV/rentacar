package nl.avans.rent_my_car

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration

import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.avans.rent_my_car.databinding.ActivityMainBinding

import androidx.navigation.findNavController

import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.NavController


import androidx.navigation.fragment.NavHostFragment





class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        //val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home, R.id.car_list_fragment
            )
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment?
        val navController = navHostFragment!!.navController

        setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
    }
}