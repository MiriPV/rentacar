package nl.avans.rent_my_car.util

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

class CustomAdapter(private val mList: List<Car>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

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

        val car = mList[position]

        //val brand = R.string.text_brand
        //val type = "Type: ${carList[position].type}"
        //val seats = "Number of seats: ${carList[position].seats}"
        //val currencyFormat = NumberFormat.getCurrencyInstance().format(carList[position].rent)
        //val cost = "Cost per hour: $currencyFormat"

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(R.drawable.ic_baseline_directions_car_24)
        holder.textViewLicencePlate.text = car.licencePlate
        holder.textViewBrand.text = car.brand
        holder.textViewType.text = car.type
        holder.textViewSeats.text = car.seatCount.toString()
        holder.textViewCost.text = car.rentPerHour.toString()

        holder.button.setOnClickListener {
            val navController = Navigation.findNavController(holder.imageView)
            //val details = TheCarDetailFragment.newInstance("volvo", "car")
            //navController.navigate(details)
            navController.navigate(R.id.theCarDetailFragment)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
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