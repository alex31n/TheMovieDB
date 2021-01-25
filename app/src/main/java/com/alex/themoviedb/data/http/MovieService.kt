package com.alex.themoviedb.data.http

import com.alex.themoviedb.model.Movies
import com.alex.themoviedb.utils.Constants
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?api_key=" + Constants.API_KEY)
    fun getMovies(@Query("page") page: Int): Observable<Response<Movies>>
}