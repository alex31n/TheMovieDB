package com.alex.themoviedb.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.alex.themoviedb.data.http.RetrofitClient
import com.alex.themoviedb.model.ListingResult
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.model.MovieResult
import com.alex.themoviedb.model.Movies
import com.alex.themoviedb.utils.Constants
import com.alex.themoviedb.utils.app.MyApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.Executors

private const val TAG = "MovieRepository"

class MovieRepository {

    private val db = MyApplication.INSTANT.database;
    private val movieDao = db.movieDao()
    private val compositeDisposable = CompositeDisposable()
    private val executor = Executors.newSingleThreadExecutor()
    private val movieApi = RetrofitClient.GET_MOVIE_API

    private fun insertIntoDB(listingResult: ListingResult) {
        if (!listingResult.items.isNullOrEmpty()) {
            executor.execute {
                db.runInTransaction {
                    val start = movieDao.getMaxIndexInTerm(listingResult.term)
                    listingResult.items.mapIndexed { index, item ->
                        item.term = listingResult.term
                        item.page = listingResult.page
                        item.indexInResponse = start + index
//                        Log.d(TAG, "insertIntoDB: "+item)
                    }
                    movieDao.insertAll(listingResult.items)
                }

            }
        }
    }

    public fun getMovies(term: String): MovieResult {
        Log.d(TAG, "getMovies: ")

        val boundaryCallback = MovieBoundaryCallback(
            webservice = movieApi,
            term = term,
            handleResponse = this::handleResponse,
            ioExecutor = executor,
            networkPageSize = Constants.PAGE_SIZE
        )
        // We use toLiveData Kotlin extension function here, you could also use LivePagedListBuilder
        val livePagedList = movieDao.getByTerm(term).toLiveData(
            boundaryCallback = boundaryCallback,
            pageSize = 10
        )

        return MovieResult(livePagedList, boundaryCallback.networkState)
    }

    public fun getRandomMoviesByTerm(term: String):LiveData<List<Movie>>{
        return movieDao.getByRandomTerm(term)
    }

    private fun handleResponse(term: String, callback: Single<Response<Movies>>, networkState: MutableLiveData<NetworkState>) {
        compositeDisposable.add(
            callback
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        Log.d(TAG, "handleResponse url: " + response.raw().request().url())
                        if (response.isSuccessful) {
                            Log.d(TAG, "handleResponse url: " + response.body())
                            val movies = response.body()
                            if (movies != null) {
                                val listing = ListingResult(movies.results, movies.page, term)
                                insertIntoDB(listing)
                            }
                            networkState.value = NetworkState.SUCCESS

                        } else {
                            Log.e(TAG, "getMoviesFromRemote: " + response.errorBody()?.string())
                            networkState.value = NetworkState.error(response.errorBody()?.string())
                        }
                    },
                    { t ->
                        Log.e(TAG, "getMoviesFromRemote error: ", t)
                        networkState.value = NetworkState.error(t.message)
                    }
                )
        )
    }

    public fun onCleared() {
        compositeDisposable.dispose()
    }
}