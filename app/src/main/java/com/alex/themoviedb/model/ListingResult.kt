package com.alex.themoviedb.model

data class ListingResult(
    val items:List<Movie>,
    val page :Int,
    val term:String
)
