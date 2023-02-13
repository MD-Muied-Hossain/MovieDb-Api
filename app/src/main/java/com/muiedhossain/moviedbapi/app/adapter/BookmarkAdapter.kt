package com.muiedhossain.moviedbapi.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.databinding.BookmarkItemBinding

class BookmarkAdapter(var callback : (bookmarkModel : BookmarkModel, value : Int) -> Unit) : ListAdapter<BookmarkModel, BookmarkAdapter.BookmarkViewHolder>(BookmarkDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        var binding = BookmarkItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return BookmarkViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        var bookmark = getItem(position)
        holder.bind(bookmark)
        holder.binding.bookmarkItemDeleteBtn.setOnClickListener {
            callback(bookmark,1)
        }
        holder.binding.root.setOnClickListener {
            callback(bookmark,2)
        }
    }
    class BookmarkViewHolder(var binding: BookmarkItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(bookmarkModel : BookmarkModel){
            binding.bookmark = bookmarkModel
        }
    }
    class BookmarkDiffUtil : DiffUtil.ItemCallback<BookmarkModel>(){
        override fun areItemsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
            return oldItem == newItem
        }
    }
}