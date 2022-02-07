package nl.avans.rent_my_car.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import nl.avans.rent_my_car.R
import nl.avans.rent_my_car.model.Car
import java.text.NumberFormat
import android.os.Bundle
import androidx.fragment.app.viewModels
import nl.avans.rent_my_car.model.CarViewModel


class CustomAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val car = carList[position]

        val context: Context = holder.itemView.context
        val brand: String = context.getString(R.string.text_brand).format(car.brand)
        val type: String = context.getString(R.string.text_type).format(car.type)
        val seats: String = context.getString(R.string.text_seats).format(car.seatCount.toString())
        val currencyFormat: String = NumberFormat.getCurrencyInstance().format(car.rentPerHour)
        val cost: String = context.getString(R.string.text_cost).format(currencyFormat)

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(R.drawable.ic_baseline_directions_car_24)
        holder.textViewLicencePlate.text = car.licencePlate
        holder.textViewBrand.text = brand
        holder.textViewType.text = type
        holder.textViewSeats.text = seats
        holder.textViewCost.text = cost

        holder.button.setOnClickListener {
            val navController = Navigation.findNavController(holder.itemView)
            //val details = TheCarDetailFragment.newInstance("volvo", "car")
            //navController.navigate(details)
            val bundle = Bundle()
            bundle.putString("licencePlate", car.licencePlate)
            bundle.putString("brand", brand)
            bundle.putString("type", type)
            car.id?.let { carId -> bundle.putLong("id", carId) }
            bundle.putString("seats", seats)
            bundle.putString("cost", cost)
            navController.navigate(R.id.theCarDetailFragment, bundle)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return carList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val button: Button = itemView.findViewById(R.id.button6)
        val textViewLicencePlate: TextView = itemView.findViewById(R.id.textView_licencePlate)
        val textViewBrand: TextView = itemView.findViewById(R.id.textView_brand)
        val textViewType: TextView = itemView.findViewById(R.id.textView_type)
        val textViewSeats: TextView = itemView.findViewById(R.id.textView_seats)
        val textViewCost: TextView = itemView.findViewById(R.id.textView_cost)
    }
}