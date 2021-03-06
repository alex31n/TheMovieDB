package com.alex.themoviedb.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alex.themoviedb.data.http.MovieApi
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.model.Movies
import com.alex.themoviedb.utils.Constants
import io.reactivex.Single
import retrofit2.Response
import java.util.concurrent.Executor

private const val TAG = "MovieBoundaryCallback"

class MovieBoundaryCallback(
    private val term: String,
    private val webservice: MovieApi,
    private val handleResponse: (String, Single<Response<Movies>>, networkState: MutableLiveData<NetworkState>) -> Unit,
    private val ioExecutor: Executor,
    private val networkPageSize: Int
) : PagedList.BoundaryCallback<Movie>() {

    private val _networkState = MutableLiveData<NetworkState>()

    // LiveData of network state.
    val networkState: LiveData<NetworkState>
        get() = _networkState

    override fun onZeroItemsLoaded() {
        Log.d(TAG, "onZeroItemsLoaded: ")
        requestData(term, 1)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Log.d(TAG, "onItemAtEndLoaded: $itemAtEnd")
        requestData(term, itemAtEnd.page + 1)
    }

    private fun requestData(term: String, page: Int) {
        if (_networkState.value == NetworkState.LOADING){
            return
        }

        _networkState.postValue(NetworkState.LOADING)
        when (term) {
            Constants.TERM_POPULAR -> handleResponse(term, webservice.getPopularMovies(page),_networkState)
            Constants.TERM_UPCOMING -> handleResponse(term, webservice.getUpcomingMovies(page),_networkState)
            Constants.TERM_TOP_RATED -> handleResponse(term, webservice.getTopRatedMovies(page),_networkState)
        }


    }
}