package com.alex.themoviedb.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alex.themoviedb.repository.NetworkState

data class MovieResult(
    // the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<Movie>>,
    // represents the network request status to show to the user
    val networkState: LiveData<NetworkState>,
)
