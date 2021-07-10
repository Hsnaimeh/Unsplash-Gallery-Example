package com.trends.gallery.core.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.getDrawableOrThrow
import androidx.core.content.res.getStringOrThrow
import androidx.core.view.isVisible
import com.trends.gallery.core.R
import com.trends.gallery.core.databinding.ViewEmptyBinding

/**
 * Created by Hisham Sanimeh.
 */
class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewEmptyBinding

    init {
        addView()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyleAttr, 0)
        initAttributes(typedArray)
        typedArray.recycle()
        isVisible = false
    }

    private fun addView() {
        binding = ViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private fun initAttributes(typedArray: TypedArray) {
        val icon = typedArray.getDrawableOrThrow(R.styleable.EmptyView_empty_icon)
        val title = typedArray.getStringOrThrow(R.styleable.EmptyView_empty_title)
        val message = typedArray.getStringOrThrow(R.styleable.EmptyView_empty_message)
        binding.imageView.setImageDrawable(icon)
        binding.textViewTitle.text = title
        binding.textViewMessage.text = message
    }
}
