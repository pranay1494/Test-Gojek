package com.example.go_jektest.base

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource{
    companion object {
        private val RESOURCE = "GLOBAL"

        private val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }

        fun getIdlingResource(): IdlingResource {
            return mCountingIdlingResource
        }
    }


}