package nl.avans.rent_my_car.model

class RentMyCarDAO : CarDAO {
    override fun getAll(): List<Car> {

        return listOf(
            Car(
                "BMW",
                "ICE",
                "AA_11_ZZ",
                4,
                20.00
            ),
            Car(
                "Daihatsu",
                "FCEV",
                "BB_88_KK",
                4,
                15.00
            ),
        )
    }

    override fun find(licencePlate: String): Car? = getAll().firstOrNull() { it.licencePlate == licencePlate }
}