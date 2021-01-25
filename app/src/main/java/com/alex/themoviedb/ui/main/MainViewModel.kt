package com.alex.themoviedb.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

//    public val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieRepository = MovieRepository()



    fun getMovies():LiveData<List<Movie>>{
//        var movies = movieRepository.getMovies(1)
        /*if (movies.value == null) {
            Log.e(TAG, "getMovies: null")
        }else{
            Log.e(TAG, "getMovies: not null")
        }*/



        return movieRepository.getMovies(1)
    }

    /*fun getMovie() {
        compositeDisposable.add(
            RetrofitClient.getMovieService.getMovies(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
//                        Log.d(TAG, "getMovie url: " + response.raw().request().url())
                        if (response.isSuccessful) {
//                            response.body(); // do something with this
//                            Log.d(TAG, "getMovie: ${response.body()}")
                            insertMovieToDatabase(response.body()!!.results)
                        } else {
                            response.errorBody(); // do something with that
//                            Log.e(TAG, "getMovie error: ${response.errorBody()}")
                        }
                    },
                    { t ->
                        Log.e(TAG, "getMovie error: ", t)
                    }
                )
        )
    }*/

    override fun onCleared() {
        super.onCleared()
        movieRepository.compositeDisposable.dispose()
        compositeDisposable.dispose()
    }

    private fun insertMovieToDatabase(movies: List<Movie>) {
        viewModelScope.launch {
            for (movie in movies){
                MovieRepository().insert(movie)
            }
        }
    }

}

/*
class MainViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
