package com.alex.themoviedb.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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
    fun insertAll(movieEntities: List<Movie>)

    @Delete
    fun delete(movieEntity: Movie)

    @Query("select * from movie WHERE term = :term ORDER BY indexInResponse ASC")
    fun getByTerm(term:String): DataSource.Factory<Int, Movie>

    @Query("SELECT MAX(indexInResponse) + 1 FROM movie WHERE term = :term")
    fun getMaxIndexInTerm(term:String) : Int
}