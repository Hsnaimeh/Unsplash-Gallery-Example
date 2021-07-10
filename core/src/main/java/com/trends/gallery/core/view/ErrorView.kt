package com.trends.gallery.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.trends.gallery.network.handler.GeneralErrorHandler
import com.trends.gallery.network.mapper.GeneralErrorMapper
import com.trends.gallery.core.databinding.ViewErrorBinding

/**
 * Created by Hisham Sanimeh.
 */
class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewErrorBinding
    private val generalErrorHandler = GeneralErrorHandler(context, GeneralErrorMapper())

    init {
        addView()
    }

    private fun addView() {
        binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)
        isVisible = false
    }

    fun handle(throwable: Throwable, retry: () -> Unit) {
        isVisible = true
        val message = generalErrorHandler.getMessage(throwable)
        val title = generalErrorHandler.getTitle(throwable)
        binding.textViewTitle.text = title
        binding.textViewMessage.text = message
        binding.buttonRetry.setOnClickListener {
            retry()
            isVisible = false
        }
    }

    fun getMessage(throwable: Throwable): String {
        return generalErrorHandler.getMessage(throwable)
    }
}
