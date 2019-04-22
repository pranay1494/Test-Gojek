package com.example.go_jektest.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.go_jektest.R
import com.example.go_jektest.utils.inTransaction
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() , HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_container)
        replaceFragment(savedInstanceState)
    }

    private fun replaceFragment(savedInstanceState: Bundle?) =
        savedInstanceState
            ?: supportFragmentManager.inTransaction { replace(R.id.blank_frame, fragment()) }


    abstract fun fragment() : Fragment
}