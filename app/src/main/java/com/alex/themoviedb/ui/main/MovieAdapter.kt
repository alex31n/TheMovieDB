package com.alex.themoviedb.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.themoviedb.BR
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.RowMovieItemBinding
import com.alex.themoviedb.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var dataSet: ArrayList<Movie> = ArrayList()
    private var context: Context? = null

    fun setItems(list: ArrayList<Movie>) {
        dataSet = list
        notifyDataSetChanged()
    }

    fun addItems(items: List<Movie>) {
        dataSet.addAll(items)
        notifyDataSetChanged()
    }

    fun getItems(): List<Movie> {
        return dataSet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }


    class ViewHolder(binding: RowMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val binding: RowMovieItemBinding = binding

        fun bind(movie: Movie) {
            binding.setVariable(BR.movie, movie)
            binding.executePendingBindings()
        }

        fun getBinding(): RowMovieItemBinding {
            return binding
        }

    }
}