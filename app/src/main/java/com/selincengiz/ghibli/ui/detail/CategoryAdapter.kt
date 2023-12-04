package com.selincengiz.ghibli.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.selincengiz.ghibli.common.Constants
import com.selincengiz.ghibli.common.Extensions.loadUrl
import com.selincengiz.ghibli.data.entities.Genre
import com.selincengiz.ghibli.databinding.ItemCategoryBinding
import com.selincengiz.ghibli.databinding.ItemTvBinding
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.ui.search.TvAdapter

class CategoryAdapter (private val itemListener: ItemTvListener) :
    ListAdapter<Genre, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CategoryViewHolder(
        private val binding: ItemCategoryBinding,
        private val listener: ItemTvListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) = with(binding) {

            tvName.text=genre.name


            root.setOnClickListener {
                listener.onClicked(genre)
            }
        }

    }

    class CategoryDiffCallBack() : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }

    }


}

interface ItemTvListener {
    fun onClicked(genre: Genre)
}
