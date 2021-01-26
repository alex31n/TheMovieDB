package com.alex.themoviedb.ui.main.details

import android.util.Log
import androidx.lifecycle.*
import com.alex.themoviedb.data.http.RetrofitClient
import com.alex.themoviedb.model.Cast
import com.alex.themoviedb.model.CastResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val TAG = "DetailsViewModel"

class DetailsViewModel : ViewModel() {

    private val movieApi = RetrofitClient.GET_MOVIE_API
    private val compositeDisposable = CompositeDisposable()

    val castResult: MutableLiveData<CastResult> = MutableLiveData()

    public fun getCredits(movieId: Int) {
        compositeDisposable.add(
            movieApi.getMovieCredit(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
//                        Log.d(TAG, "getCredits url: " + response.raw().request().url())
                        if (response.isSuccessful) {
//                            Log.d(TAG, "getCredits url: " + response.body())
                            castResult.value = response.body()
                        } else {
                            Log.e(
                                TAG, "getCredits: " + response.errorBody()?.string()
                            )
                        }
                    },
                    { t ->
                        Log.e(TAG, "getCredits error: ", t)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}


