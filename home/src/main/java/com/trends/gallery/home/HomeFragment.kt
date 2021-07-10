package com.trends.gallery.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paginate.Paginate
import com.trends.gallery.core.view.PaginationListItemCreator
import com.trends.gallery.data.model.Photo
import com.trends.gallery.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), Paginate.Callbacks {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private var adapter: PhotosAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        initListeners()
        initObservers()
    }

    override fun onLoadMore() {
        viewModel.onLoadMore()
    }

    override fun isLoading(): Boolean {
        return viewModel.isLoading
    }

    override fun hasLoadedAllItems(): Boolean {
        return viewModel.hasLoadedAllItems
    }

    private fun initObservers() {
        viewModel.progressLiveData.observe(viewLifecycleOwner, ::handleProgress)
        viewModel.successLiveData.observe(viewLifecycleOwner, ::handleSuccess)
        viewModel.errorLiveData.observe(viewLifecycleOwner, ::handleError)
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshPhotos()
        }
    }

    private fun handleProgress(show: Boolean) {
        if (!show) {
            binding.swipeRefreshLayout.isRefreshing = show
        }
        binding.progressBar.isVisible = show && adapter == null
    }

    private fun handleSuccess(orders: List<Photo>) {
        binding.emptyView.isVisible = orders.isEmpty() && adapter == null
        if (adapter == null) {
            adapter = PhotosAdapter( viewModel)
        }
        if (binding.recyclerView.adapter == null) {
            binding.recyclerView.adapter = adapter
            initPagination()
        }
        adapter?.notifyDataSetChanged()
    }

    private fun initPagination() {
        Paginate.with(binding.recyclerView, this)
            .setLoadingTriggerThreshold(2)
            .addLoadingListItem(true)
            .setLoadingListItemCreator(PaginationListItemCreator())
            .build()
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        if (adapter != null) {
            Toast.makeText(
                requireContext(),
                binding.errorView.getMessage(throwable),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        binding.errorView.handle(throwable) { viewModel.onCreate() }
    }



}
