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
import com.selincengiz.ghibli.databinding.ItemVideoBinding
import com.selincengiz.ghibli.domain.entities.TvVideo

class VideoAdapter (private val itemListener: ItemVideoListener) :
    ListAdapter<TvVideo, VideoAdapter.VideoViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
        VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) =
        holder.bind(getItem(position))

    class VideoViewHolder(
        private val binding: ItemVideoBinding,
        private val listener: ItemVideoListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: TvVideo) = with(binding) {

            tvName.text=video.name
            val request = RequestOptions()
            val requestOptions = request.transforms(CenterCrop(), RoundedCorners(40))
            ivPhoto.loadUrl(Constants.IMAGE_URL + video.photo, requestOptions)



            root.setOnClickListener {
                listener.onClicked(video)
            }
        }

    }

    class CategoryDiffCallBack() : DiffUtil.ItemCallback<TvVideo>() {
        override fun areItemsTheSame(oldItem: TvVideo, newItem: TvVideo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TvVideo, newItem: TvVideo): Boolean {
            return oldItem == newItem
        }

    }

}

interface ItemVideoListener {
    fun onClicked(video: TvVideo)
}
