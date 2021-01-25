package com.alex.themoviedb.utils.app

import android.app.Application
import com.alex.themoviedb.data.database.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication :Application(){

    companion object{
        lateinit var INSTANT : MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANT = this;
    }

    val applicationScope = CoroutineScope(SupervisorJob())

    public val database by lazy { MovieDatabase.getDatabase(this) }
//    val repository by lazy { MovieDatabase(database.movieDao()) }
}