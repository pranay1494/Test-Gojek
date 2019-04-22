package com.example.go_jektest.base

import com.example.go_jektest.exceptions.Failure

sealed class ViewStatus {
    data class SUCCESS(val message : String) : ViewStatus()
    data class FAIL(val failure : Failure) : ViewStatus()
    data class LOADING(val message : String) : ViewStatus()

    //use this generic object when o customization needed
    companion object {

        val LOADING = ViewStatus.LOADING("loading......")
        val SUCCESS = ViewStatus.SUCCESS("SUCCESS")

    }

}