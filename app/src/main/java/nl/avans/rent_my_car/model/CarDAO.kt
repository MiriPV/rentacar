package nl.avans.rent_my_car.model

interface CarDAO {
    fun getAll(): List<Car>
    fun find(licencePlate: String): Car?
}