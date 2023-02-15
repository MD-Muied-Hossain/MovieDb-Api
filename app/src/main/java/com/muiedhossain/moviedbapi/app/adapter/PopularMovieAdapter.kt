package com.muiedhossain.moviedbapi.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.app.model.PopularMovieResult
import com.muiedhossain.moviedbapi.databinding.PopulerMovieItemBinding

class PopularMovieAdapter(val callback: (movie: PopularMovieResult,binding : PopulerMovieItemBinding,value : Int) -> Unit) :
    ListAdapter<PopularMovieResult, PopularMovieAdapter.PopularMovieViewHolder>(PopularMovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = PopulerMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        var result = getItem(position)
        holder.bind(result)
        callback(result,holder.binding,2)

        Log.e("callback", "onCreateView: Popular onBindViewHolder" )

        holder.binding.poppularCardItem.setOnClickListener {
            ConstraintUtils.movieDetails.selectedMovieID = result.id
            callback(result,holder.binding,1)
        }
    }

    class PopularMovieViewHolder(var binding: PopulerMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(popularMovie: PopularMovieResult) {
            binding.popular = popularMovie
        }
    }

    class PopularMovieDiffUtil : DiffUtil.ItemCallback<PopularMovieResult>() {
        override fun areItemsTheSame(oldItem: PopularMovieResult, newItem: PopularMovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularMovieResult, newItem: PopularMovieResult): Boolean {
            return oldItem == newItem
        }
    }
}