package com.alex.themoviedb.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.RowCastItemBinding
import com.alex.themoviedb.databinding.RowHomeMovieItemBinding
import com.alex.themoviedb.model.Cast
import com.alex.themoviedb.model.Movie

class HomeMovieAdapter(private val movieItemClick: (Movie) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list  = mutableListOf<Movie>()

    fun setItems(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Movie>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    fun getItems(): List<Movie> {
        return this.list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_home_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = this.list[position]
        (holder as ViewHolder).bind(movie)

        holder.itemView.setOnClickListener{
            movieItemClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }


    class ViewHolder(binding: RowHomeMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val binding: RowHomeMovieItemBinding = binding

        fun bind(movie: Movie) {
            binding.setVariable(BR.movie, movie)
            binding.executePendingBindings()
        }

        fun getBinding(): RowHomeMovieItemBinding {
            return binding
        }

    }
}