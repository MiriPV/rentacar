package nl.avans.rent_my_car.model

data class Car(
    val brand: String,
    val type: String,
    val licencePlate: String,
    val seatCount: Int,
    val rentPerHour: Double
)