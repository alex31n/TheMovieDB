package com.alex.themoviedb.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.alex.themoviedb.data.database.dao.MovieDao
import com.alex.themoviedb.data.http.RetrofitClient
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.utils.app.MyApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val TAG = "MovieRepository"

class MovieRepository {

    val movieDao: MovieDao = MyApplication.INSTANT.database.movieDao()
    val compositeDisposable = CompositeDisposable()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }


    private  val mSectionLive : MediatorLiveData<List<Movie>> = MediatorLiveData();

    fun getMovies(page: Int): LiveData<List<Movie>> {

        val movies: LiveData<List<Movie>> = movieDao.getAll()

        mSectionLive.addSource(movies, Observer { movieList->
            if(movieList == null || movieList.isEmpty()) {
                // Fetch data from API
                Log.e(TAG, "getMovies: null")
                getMoviesFromRemote(page)
            }else{
                mSectionLive.removeSource(movies);
                mSectionLive.value = movieList;
                Log.e(TAG, "getMovies: not null")
            }
        })

        return mSectionLive
    }


    // fetch data from remote api
    private fun getMoviesFromRemote(page: Int) {
        Log.d(TAG, "getMoviesFromRemote: ")
        compositeDisposable.add(
            RetrofitClient.getMovieService.getMovies(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        if (response.isSuccessful) {
                            CoroutineScope(SupervisorJob()).launch {
                                run {
                                    insertAll(response.body()?.results!!)
                                }
                            }
                        } else {
                            Log.e(TAG, "getMoviesFromRemote: "+response.errorBody()?.string() )
                        }
                    },
                    { t ->
                        Log.e(TAG, "getMoviesFromRemote error: ", t)
                    }
                )
        )
    }
}