package nl.avans.rent_my_car
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import nl.avans.rent_my_car.ui.cars.Car

class CustomAdapter
(private val mDataSet: Array<Any>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            v.setOnClickListener {
                Log.d(
                    TAG,
                    "Element $adapterPosition clicked."
                )
            }
            val textViewBrand  = v.findViewById<TextView>(R.id.tvBrand)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.car_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")
        viewHolder.textView.text = mDataSet[position]
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    companion object {
        private const val TAG = "CustomAdapter"
    }
}