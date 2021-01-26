package com.alex.themoviedb.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alex.themoviedb.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import org.apache.commons.lang3.StringUtils

object BindingAdapterHelper {
    /*@BindingAdapter("backgroundColor")
    fun backgroundColor(view: View, color: Int) : Unit{
        view.setBackgroundColor(color)
    }

    @BindingAdapter("textColor")
    fun textColor(view: TextView, color: Int) : Unit{
        view.setTextColor(color)
    }*/


}

@BindingAdapter("image")
fun image(view: ImageView?, image: String?) : Unit{
//        ImageHelper.loadImage(view, image);

    Glide.with(view!!.context)
        .load(image) //.placeholder(R.drawable.img_default_thumb)
        .thumbnail(0.25f)
//            .error(R.drawable.img_default_thumb)
        .apply(
            RequestOptions()
//            .placeholder(R.drawable.img_default_thumb)
            .error(R.drawable.img_default_thumb)
        )
        .transition(DrawableTransitionOptions.withCrossFade())
//            .listener(listener)
        .into(view)
}