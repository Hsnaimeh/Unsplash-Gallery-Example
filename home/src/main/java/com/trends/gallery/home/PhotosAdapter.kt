package com.trends.gallery.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trends.gallery.data.utils.doOnClick
import com.trends.gallery.home.databinding.RowPhotoDefaultBinding

/**
 * Created by Hisham Sanimeh
 */
class PhotosAdapter(
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowPhotoDefaultBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return viewModel.photos.size
    }


    inner class ViewHolder(private val binding: RowPhotoDefaultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.photo = viewModel.photos[position]
            binding.executePendingBindings()

            itemView.doOnClick(::onPhotoClicked)

        }

        private fun onPhotoClicked(view: View) {
            val clickedPosition = adapterPosition
            if (clickedPosition != RecyclerView.NO_POSITION) {

            }
        }

    }
}
