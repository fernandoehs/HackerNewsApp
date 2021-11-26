package com.fernandoherrera.hackernewsapp.ui.main.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fernandoherrera.hackernewsapp.R
import com.fernandoherrera.hackernewsapp.databinding.ItemHitBinding
import com.fernandoherrera.hackernewsapp.ui.main.handler.HitItemListener
import com.fernandoherrera.hackernewsapp.ui.main.model.HitItem

class HitItemViewHolder(val binding: ItemHitBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hitItem: HitItem, listener: HitItemListener?) {
        with(binding) {
            this.model = hitItem
            this.listener = listener
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): HitItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hit, parent, false)
            val binding = ItemHitBinding.bind(view)
            return HitItemViewHolder(binding)
        }
    }
}
