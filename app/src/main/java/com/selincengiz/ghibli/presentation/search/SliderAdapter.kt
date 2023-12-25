package com.selincengiz.ghibli.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.selincengiz.ghibli.common.Constants.IMAGE_URL
import com.selincengiz.ghibli.common.Extensions.loadUrl
import com.selincengiz.ghibli.databinding.ItemSliderBinding
import com.selincengiz.ghibli.domain.entities.Tv


class SliderAdapter(private val itemListener: ItemSliderListener) :
    ListAdapter<Tv, SliderAdapter.SliderViewHolder>(CartDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SliderViewHolder(
        private val binding: ItemSliderBinding,
        private val listener: ItemSliderListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(discoverTv: Tv) = with(binding) {

            val request = RequestOptions()
            val requestOptions = request.transforms(CenterCrop(), RoundedCorners(60))
            imageSlide.loadUrl(IMAGE_URL + discoverTv.posterPath, requestOptions)


            root.setOnClickListener {
                listener.onClicked(discoverTv)
            }
        }

    }

    class CartDiffCallBack() : DiffUtil.ItemCallback<Tv>() {
        override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
            return oldItem == newItem
        }

    }


}

interface ItemSliderListener {
    fun onClicked(tv: Tv)

}
