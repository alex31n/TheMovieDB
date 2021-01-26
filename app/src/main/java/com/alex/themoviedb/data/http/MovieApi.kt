package com.alex.themoviedb.data.http

import com.alex.themoviedb.model.CastResult
import com.alex.themoviedb.model.Movies
import com.alex.themoviedb.utils.Constants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?api_key=" + Constants.API_KEY)
    fun getPopularMovies(@Query("page") page: Int): Single<Response<Movies>>

    @GET("movie/{id}/credits?api_key=" + Constants.API_KEY)
    fun getMovieCredit(@Path("id") id :Int): Single<Response<CastResult>>


}