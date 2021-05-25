package com.test.skydemo.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.lib_api.domain.model.News
import com.test.skydemo.databinding.LayoutNewsItemBinding
import com.test.skydemo.ui.extension.formattedString
import com.test.skydemo.ui.extension.loadRoundedImage

class NewsAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    object DiffUtilCallback : DiffUtil.ItemCallback<News.Item>() {
        override fun areItemsTheSame(oldItem: News.Item, newItem: News.Item): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: News.Item, newItem: News.Item): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(
        this,
        DiffUtilCallback
    )

    val data: List<News.Item>
        get() = listDiffer.currentList

    init {
        hasStableIds()
    }

    fun update(newData: List<News.Item>) {
        listDiffer.submitList(newData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutNewsItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(private val binding: LayoutNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: News.Item, clickListener: OnItemClickListener) {
            binding.newsHeadline.text = newsItem.headline
            binding.newsTeaserText.text = newsItem.teaserText
            binding.newsCreationDate.text = newsItem.creationDate.formattedString
            binding.newsTeaserImage.loadRoundedImage(newsItem.teaserImageUrl.href)
            binding.newsItemContainer.setOnClickListener {
                clickListener.onItemClicked(newsItem)
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(newsItem: News.Item)
}
