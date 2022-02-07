package nl.avans.rent_my_car.model

import com.squareup.moshi.Json

data class Car(
    @Json(name = "brand")
    val brand: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "user_id")
    val user_id: Int? = null,
    @Json(name = "licencePlate")
    val licencePlate: String,
    @Json(name = "seatCount")
    val seatCount: Int,
    @Json(name = "rentPerHour")
    val rentPerHour: Double
)