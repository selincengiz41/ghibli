package com.selincengiz.ghibli.presentation.search

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
import com.selincengiz.ghibli.databinding.ItemTvBinding
import com.selincengiz.ghibli.domain.entities.Tv


class TvAdapter(private val itemListener: ItemTvListener) :
    ListAdapter<Tv, TvAdapter.TvViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder =
        TvViewHolder(
            ItemTvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.bind(getItem(position))

    class TvViewHolder(
        private val binding: ItemTvBinding,
        private val listener: ItemTvListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trendTv: Tv) = with(binding) {

            val request = RequestOptions()
            val requestOptions = request.transforms(CenterCrop(), RoundedCorners(60))
            imageTv.loadUrl(Constants.IMAGE_URL + trendTv.posterPath, requestOptions)

            textName.text = trendTv.name


            root.setOnClickListener {
                listener.onClicked(trendTv)
            }
        }

    }

    class CategoryDiffCallBack() : DiffUtil.ItemCallback<Tv>() {
        override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
            return oldItem == newItem
        }

    }


}

interface ItemTvListener {
    fun onClicked(tv: Tv)
}
