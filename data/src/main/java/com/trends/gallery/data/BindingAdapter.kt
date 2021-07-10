package com.trends.gallery.data

import android.graphics.Paint
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.trends.gallery.data.glide.GlideApp

/**
 * Created by Hisham Sanimeh.
 */
@BindingAdapter("srcCompat")
fun ImageView.setSrcCompat(src: Int) {
    setImageResource(src)
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    if (url == null) {
        setImageBitmap(null)
        return
    }
    try {
        GlideApp.with(this).load(url).into(this)
    } catch (throwable: Throwable) {
        // No impl
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(images: MediaStore.Images?) {
    if (images == null) {
        setImageBitmap(null)
        return
    }
    try {
        GlideApp.with(this).load(images).into(this)
    } catch (throwable: Throwable) {
        // No impl
    }
}

@BindingAdapter("selected")
fun View.setBindingSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("strikeThru")
fun TextView.setStrikeThru(strikeThru: Boolean) {
    paintFlags = if (strikeThru) {
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("backgroundColor")
fun View.setBindingBackgroundColor(color: Int) {
    setBackgroundColor(color)
}

@BindingAdapter("backgroundTint")
fun View.setBindingBackgroundTint(tint: Int?) {
    if (tint == null) {
        return
    }
    DrawableCompat.setTint(DrawableCompat.wrap(background).mutate(), tint)
}
