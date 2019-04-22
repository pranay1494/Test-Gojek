package com.example.go_jektest.base

import com.example.go_jektest.exceptions.Failure
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.disposables.DisposableHelper
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.atomic.AtomicReference

abstract class BaseSubscriber<T> : AtomicReference<Disposable>(),Observer<T>, Disposable {

    override fun onError(e: Throwable) {

        if (!isDisposed) {
            lazySet(DisposableHelper.DISPOSED)
            try {
                if (e is HttpException) {
                    if(e.code() == 401)
                        onFailure(Failure.AuthenticationError("Your session has expired!"))
                    else
                        onFailure(Failure.HttpFailure(e.message(),e))
                } else if (e is IOException) {
                    onFailure(Failure.NetworkConnection("You are not connected to Internet!"))
                } else if (e is Failure) {
                    onFailure(e)
                }  else {
                    onFailure(Failure.ServerError("Something went wrong at out end!"))
                }
                e.printStackTrace()
            } catch (e: Throwable) {
                Exceptions.throwIfFatal(e)
                RxJavaPlugins.onError(CompositeException(e, e))
            }

        } else {
            RxJavaPlugins.onError(e)
        }
    }




    override fun onComplete() {
        if (!isDisposed) {
            lazySet(DisposableHelper.DISPOSED)
            try {
            } catch (e: Throwable) {
                Exceptions.throwIfFatal(e)
                RxJavaPlugins.onError(e)
            }

        }
    }

    override fun dispose() {
        DisposableHelper.dispose(this)
    }

    override fun isDisposed(): Boolean {
        return get() === DisposableHelper.DISPOSED
    }

    abstract fun onFailure(failure: Failure)


}
