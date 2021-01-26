package com.alex.themoviedb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.themoviedb.BR
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.RowMovieItemBinding
import com.alex.themoviedb.model.Movie
import kotlin.reflect.KFunction1

private const val TAG = "MovieAdapter"
class MovieAdapter(private val movieItemClick: (Movie) -> Unit) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(REPO_COMPARATOR){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
         val viewHolder = holder as ViewHolder;
//        Log.d(TAG, "onBindViewHolder: "+movie)
        if (movie != null) {
            viewHolder.bind(movie)

            viewHolder.itemView.setOnClickListener {
                movieItemClick(movie)
            }
        }



    }



    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
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