package nl.avans.rent_my_car.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.rent_my_car.model.Car
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:8080/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface CarService {
    @GET("cars")
    suspend fun getCars(): List<Car>

    @POST("cars")
    suspend fun postCar(@Body car: Car): Car

    @DELETE("cars")
    suspend fun deleteCar(@Query("car_id") CarId: Long)
}

object CarApi {
    val carService: CarService by lazy {
        retrofit.create(CarService::class.java)
    }
}


