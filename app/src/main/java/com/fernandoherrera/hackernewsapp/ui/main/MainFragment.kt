package com.fernandoherrera.hackernewsapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.fernandoherrera.hackernewsapp.R
import com.fernandoherrera.hackernewsapp.databinding.FragmentMainBinding
import com.fernandoherrera.hackernewsapp.ui.main.adapter.HitListAdapter
import com.fernandoherrera.hackernewsapp.ui.main.adapter.HitLoadStateAdapter
import com.fernandoherrera.hackernewsapp.ui.main.handler.HitItemListener
import com.fernandoherrera.hackernewsapp.ui.main.handler.SwipeToDeleteCallback
import com.fernandoherrera.hackernewsapp.ui.main.model.HitItem
import com.fernandoherrera.hackernewsapp.ui.main.viewholder.HitItemViewHolder
import com.fernandoherrera.hackernewsapp.utils.EXTRA_STORY_ID
import com.fernandoherrera.hackernewsapp.utils.showErrorMessage
import com.fernandoherrera.hackernewsapp.utils.showWarningMessage
import com.wada811.databinding.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment :
    Fragment(R.layout.fragment_main),
    HitItemListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: MainViewModel by viewModel()
    private val binding: FragmentMainBinding by dataBinding()
    private val adapter: HitListAdapter = HitListAdapter(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        obsHits()
        obsItemRemoved()
    }

    override fun onPause() {
        adapter.removeLoadStateListener {
            Log.e("MainFragmet", "LoadStateListener Removed")
        }
        super.onPause()
    }

    private fun initUI() {
        with(binding) {
            srlRefresh.setOnRefreshListener(this@MainFragment)
            initAdapterLoadingState()
            initSwipeToDeleteHandler()
            rvHits.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            rvHits.adapter =
                adapter.withLoadStateFooter(footer = HitLoadStateAdapter { adapter.retry() })
            executePendingBindings()
        }
    }

    private fun initAdapterLoadingState() {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.srlRefresh.isRefreshing = true
            } else {
                binding.srlRefresh.isRefreshing = false

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    requireContext().showErrorMessage(
                        it.error.message ?: getString(R.string.generic_error)
                    )
                }
            }
        }
    }

    private fun initSwipeToDeleteHandler() {
        val swipeToDeleteHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val item = (viewHolder as HitItemViewHolder).binding.model!!
                viewModel.removeHitItem(item.objectId)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvHits)
    }

    private fun obsHits() {
        viewModel.allHits.observe(
            viewLifecycleOwner,
            {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        )
    }

    private fun obsItemRemoved() {
        viewModel.itemRemoved.observe(
            viewLifecycleOwner,
            {
                if (it) {
                    requireContext().showWarningMessage(getString(R.string.item_removed_message))
                }
            }
        )
    }

    override fun onHitClick(item: HitItem) {
        findNavController().navigate(
            R.id.action_nav_main_to_nav_detail,
            bundleOf(
                EXTRA_STORY_ID to item.storyUrl
            )
        )
    }

    override fun onRefresh() {
        adapter.refresh()
    }

    override fun onDestroyView() {
        if (binding.rvHits.adapter != null)
            binding.rvHits.adapter = null
        super.onDestroyView()

    }
}
