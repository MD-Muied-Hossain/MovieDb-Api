package com.muiedhossain.moviedbapi.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.databinding.GenresItemBinding

class GenresHorizontalViewAdapter : androidx.recyclerview.widget.ListAdapter<Genre,GenresHorizontalViewAdapter.GenresItemViewHolder>(GenresDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresItemViewHolder {
        var binding = GenresItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return GenresItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresItemViewHolder, position: Int) {
        var genresResult = getItem(position)
        holder.bind(genresResult)
    }
    class GenresItemViewHolder(val binding : GenresItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(genres : Genre){
                binding.genres = genres
            }
    }

    class GenresDiffUtil : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }


}