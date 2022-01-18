package nl.avans.rent_my_car

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoUITest {
    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun check_if_all_menu_items_are_available() {
        onView(withId(R.id.home)).check(matches(isDisplayed()))
        onView(withId(R.id.car_list_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.registrationFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun check_text_on_all_buttons() {
        onView(withId(R.id.car_list_fragment)).perform(ViewActions.click())
        onView(allOf(withId(R.id.button6), withText("Click to rent")))
    }

    @Test
    fun check_car_registration() {
        onView(withId(R.id.registrationFragment)).perform(ViewActions.click())
        onView(withId(R.id.brand)).perform(typeText("BMW"))
        onView(withId(R.id.type)).perform(typeText("ICE"))
        onView(withId(R.id.lp)).perform(typeText("AA-11-ZZ"))
        onView(withId(R.id.seats)).perform(typeText("4"))
        onView(withId(R.id.cost)).perform(typeText("10"))
        onView(withId(R.id.button_send)).perform(closeSoftKeyboard(), click())
        onView(withId(R.id.textView_result)).check(matches(withText("car not posted. HTTP 400 Car(brand=BMW, type=ICE, licencePlate=AA-11-ZZ, seatCount=4, rentPerHour=10.0)")))
    }

}