package com.alex.themoviedb.ui.main.popular

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.model.MovieResult
import com.alex.themoviedb.repository.MovieRepository
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class PopularViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    private val termLiveData = MutableLiveData<String>()
     val movieResult : LiveData<MovieResult> = Transformations.map(termLiveData) {
        movieRepository.getMovies(it)
    }

    val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(movieResult) { it.pagedList }

    fun searchMovies(term: String) {
        termLiveData.postValue(term)
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.onCleared()
    }


}


