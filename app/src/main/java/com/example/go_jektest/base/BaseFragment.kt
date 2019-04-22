package com.example.go_jektest.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import com.example.go_jektest.R
import com.example.go_jektest.utils.setVisible
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_splash.*
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var retry : ()->Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base,container,false)
        val baseContainer = view.findViewById<FrameLayout>(R.id.frameContent)
        baseContainer.addView(inflater.inflate(layoutId(),container,false))
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        provideBaseModel().getViewStatus().observe(this, Observer {
            when(it){
                ViewStatus.LOADING->{
                    showLoading()
                }
                is ViewStatus.FAIL->{
                    showError(it.failure.message)
                    retry = it.failure.retry
                }
                else->{
                    ivLoading.setVisible(false)
                    frameContent.setVisible(true)
                    error_layout.setVisible(false)
                    stopLoadingAnimation()
                }
            }
        })

        btnRetry.setOnClickListener {
            retry()
        }
    }

    private fun showError(message: String) {
        ivLoading.setVisible(false)
        frameContent.setVisible(false)
        error_layout.setVisible(true)
        tvError.text = message
        stopLoadingAnimation()
    }

    private fun showLoading() {
        ivLoading.setVisible(true)
        frameContent.setVisible(false)
        error_layout.setVisible(false)
        startLoadingAnimation()
    }

    private fun startLoadingAnimation() {
        if (activity != null) {
            val rotation = AnimationUtils.loadAnimation(activity, R.anim.loader)
            ivLoader.startAnimation(rotation)
        }
    }

    private fun stopLoadingAnimation(){
        ivLoader.clearAnimation()
    }

    abstract fun provideBaseModel() : BaseViewModel

    abstract fun layoutId(): Int
}