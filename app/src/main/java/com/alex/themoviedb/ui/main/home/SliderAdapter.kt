package com.alex.themoviedb.ui.main.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.themoviedb.BR
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.ImageSliderLayoutItemBinding
import com.alex.themoviedb.databinding.RowMovieItemBinding
import com.alex.themoviedb.model.Movie
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter (private val movieItemClick: (Movie) -> Unit): SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    private var items  = mutableListOf<Movie>()


    fun newItems(sliderItems: List<Movie>) {
        items.clear()
        items.addAll(sliderItems)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(movie: Movie) {
        items.add(movie)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder? {
        /*val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)*/
        return SliderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.image_slider_layout_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        val movie: Movie = items[position]
        viewHolder.bind(movie)

        viewHolder.itemView.setOnClickListener{
            movieItemClick(movie)
        }

    }


    class SliderViewHolder(binding: ImageSliderLayoutItemBinding) : ViewHolder(binding.root) {

        private val binding: ImageSliderLayoutItemBinding = binding

        fun bind(movie: Movie) {
            binding.setVariable(BR.movie, movie)
            binding.executePendingBindings()
        }

        fun getBinding(): ImageSliderLayoutItemBinding {
            return binding
        }

    }
}