package com.example.go_jektest.utils

import android.app.Activity
import android.view.View
import android.widget.Toast

fun View.setVisible(isVisible : Boolean){
    this.visibility = if(isVisible) View.VISIBLE else View.GONE
}

fun Toast.display(activity: Activity?, msg : String, isLong : Boolean){
    Toast.makeText(activity,msg,if (isLong)Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}