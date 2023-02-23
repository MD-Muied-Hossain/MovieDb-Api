package com.muiedhossain.moviedbapi.app.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.databinding.NowshowingMovieItemBinding
import com.muiedhossain.moviedbapi.app.view.home.model.Result

class NowShowingMovieAdapter(val callback: (movie: Result) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Result,
            NowShowingMovieAdapter.NowShowingMovieViewHolder>(NowShowingDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingMovieViewHolder {
        val binding =
            NowshowingMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowShowingMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowShowingMovieViewHolder, position: Int) {
        var result = getItem(position)
        holder.bind(result)
        holder.binding.root.setOnClickListener {
            ConstraintUtils.movieDetails.selectedMovieID = result.id
            callback(result)
        }
    }

    class NowShowingMovieViewHolder(val binding: NowshowingMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nowShowing: Result) {
            binding.nowShowing = nowShowing
        }
    }

    class NowShowingDiffUtil : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}