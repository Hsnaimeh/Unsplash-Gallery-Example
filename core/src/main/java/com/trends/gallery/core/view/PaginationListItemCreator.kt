package com.trends.gallery.core.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paginate.recycler.LoadingListItemCreator
import com.trends.gallery.core.R

/**
 * Created by Hisham Sanimeh.
 */
class PaginationListItemCreator : LoadingListItemCreator {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.row_loading, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        // No impl
    }
}
