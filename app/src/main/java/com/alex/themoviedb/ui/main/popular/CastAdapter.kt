package com.alex.themoviedb.ui.main.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.RowCastItemBinding
import com.alex.themoviedb.model.Cast

class CastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<Cast> = ArrayList()

    fun setItems(list: ArrayList<Cast>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addItems(items: List<Cast>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    fun getItems(): List<Cast> {
        return this.list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_cast_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cast = this.list[position]
        (holder as ViewHolder).bind(cast)

    }

    override fun getItemCount(): Int {
        return this.list.size
    }


    class ViewHolder(binding: RowCastItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val binding: RowCastItemBinding = binding

        fun bind(cast: Cast) {
            binding.setVariable(BR.cast, cast)
            binding.executePendingBindings()
        }

        fun getBinding(): RowCastItemBinding {
            return binding
        }

    }
}