package com.example.go_jektest.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.go_jektest.exceptions.Failure
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){
    internal var viewStatus : MutableLiveData<ViewStatus> = MutableLiveData()
    protected var compositeDisposable = CompositeDisposable()
    internal fun getViewStatus() : LiveData<ViewStatus> = viewStatus

    open internal fun handleError(e: Failure){
        when(e){
            is Failure.NetworkConnection -> viewStatus.postValue(ViewStatus.FAIL(e))
            is Failure.ServerError -> viewStatus.postValue(ViewStatus.FAIL(e))
            else -> viewStatus.postValue(ViewStatus.FAIL(e))
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}