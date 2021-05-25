package com.test.skydemo.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.skydemo.databinding.LayoutStoryDetailItemBinding
import com.test.skydemo.ui.extension.loadImage
import com.test.lib_api.domain.model.Story.*

class NewsDetailAdapter : RecyclerView.Adapter<NewsDetailAdapter.ViewHolder>() {

    object DiffUtilCallback : DiffUtil.ItemCallback<ContentsItem>() {
        override fun areItemsTheSame(oldItem: ContentsItem, newItem: ContentsItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ContentsItem, newItem: ContentsItem): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(
        this,
        DiffUtilCallback
    )

    private val data: List<ContentsItem>
        get() = listDiffer.currentList

    init {
        hasStableIds()
    }

    fun update(newData: List<ContentsItem>) {
        listDiffer.submitList(newData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutStoryDetailItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(private val binding: LayoutStoryDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: ContentsItem) {
            when (newsItem.type) {
                is ContentsItem.Type.Paragraph -> {
                    binding.storyText.text = (newsItem.type as ContentsItem.Type.Paragraph).text
                }
                is ContentsItem.Type.Image -> {
                    binding.storyImage.loadImage((newsItem.type as ContentsItem.Type.Image).url)
                }
            }
        }
    }
}