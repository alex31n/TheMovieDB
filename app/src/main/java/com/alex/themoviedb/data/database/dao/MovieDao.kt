package com.alex.themoviedb.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alex.themoviedb.model.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("select * from movie")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movieEntity: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntities: List<Movie>)

    @Delete
    suspend fun delete(movieEntity: Movie)
}