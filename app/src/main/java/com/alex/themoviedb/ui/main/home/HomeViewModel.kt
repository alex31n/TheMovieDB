package com.alex.themoviedb.ui.main.home

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.model.MovieResult
import com.alex.themoviedb.repository.MovieRepository
import com.alex.themoviedb.utils.Constants
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class HomeViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    val pagedListPopularMovies = movieRepository.getMovies(Constants.TERM_POPULAR)
    val pagedListUpcomingMovies = movieRepository.getMovies(Constants.TERM_UPCOMING)
    val pagedListTopRatedMovies = movieRepository.getMovies(Constants.TERM_TOP_RATED)

    override fun onCleared() {
        super.onCleared()
        movieRepository.onCleared()
    }


}


