package com.example.go_jektest.feature

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.go_jektest.R
import kotlinx.android.synthetic.main.frgment_forecast.view.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.go_jektest.base.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherInfoFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)

    @Before
    fun initialiseLoginTest(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun test_recyclerview_visible(){
        onView((ViewMatchers.withId(R.id.rvData))).check(matches(isDisplayed()))
        onView((ViewMatchers.withId(R.id.btnRetry))).check(matches(not(isDisplayed())))
        onView(ViewMatchers.withId(R.id.tvCurrentTemp)).check(matches(not(withText(""))))
    }

    @Test
    fun test_recyclerview_items_nonzero(){
        onView(withId(R.id.rvData)).check(matches(hasChildCount(4)))
    }

    @Test
    fun isCurrentTempVisible(){
        onView(ViewMatchers.withId(R.id.tvCurrentTemp)).check(matches(not(withText(""))))
    }

    @After
    fun detachLoginTest(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }
}