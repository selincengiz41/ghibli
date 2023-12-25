package com.selincengiz.ghibli.presentation.seek

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
import com.selincengiz.ghibli.databinding.ItemVideoBinding
import com.selincengiz.ghibli.domain.entities.Tv

class SearchAdapter(private val itemListener: ItemListener) :
    ListAdapter<Tv, SearchAdapter.SearchViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SearchViewHolder(
        private val binding: ItemVideoBinding,
        private val listener: ItemListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tv: Tv) = with(binding) {

            tvName.text=tv.name
            val request = RequestOptions()
            val requestOptions = request.transforms(CenterCrop(), RoundedCorners(40))
            ivPhoto.loadUrl(Constants.IMAGE_URL + tv.posterPath, requestOptions)



            root.setOnClickListener {
                listener.onClicked(tv)
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

interface ItemListener {
    fun onClicked(tv: Tv)
}
