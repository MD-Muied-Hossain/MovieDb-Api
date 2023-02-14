package com.muiedhossain.moviedbapi.app.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.app.adapter.GenresHorizontalViewAdapter
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.viewModel.MovieDetailsViewModel
import com.muiedhossain.moviedbapi.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var genresAdapter: GenresHorizontalViewAdapter
    private var genresList = ArrayList<Genre>()
    private lateinit var bookmarkModel: BookmarkModel
    private var isBookmarked: Boolean = false

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)

        viewModel.getMovieDetails().observe(viewLifecycleOwner) {
            bookmarkedCompleted()

            genresAdapter = GenresHorizontalViewAdapter()
            /////////bindings
            var hour = it.runtime / 60
            var minute = it.runtime % 60
            var time = hour.toString() + "h " + minute.toString() + "min"

            binding.movieDetailsLengthTVID.text = "$hour h $minute min"
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500" + it.backdrop_path)
                .into(binding.movieDetailsImage)
            binding.movieDetailsTitle.text = it.title
            binding.movieDetailsStarRating.text = it.vote_average.toString()
            binding.movieDetailsLanguageTVID.text = it.original_language
            binding.singleMovieDetailsDescriptionTVID.text = it.overview
            binding.movieDetailsGenresRV.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                adapter = genresAdapter
            }
            /////////bindings


            ////////////////Genres
            var genresString : String? = null
            val builder = StringBuilder()
            genresList.clear()
            for(i in 0 until it.genres.size -1){
                Log.e("genre", "onCreateView: genre" )
                genresList.add(Genre(it.genres[i].id, it.genres[i].name))
                genresString = builder.append(genresList[i].name)
                    .append(",")
                    .toString()
            }


            genresAdapter.submitList(genresList)
            genresAdapter.notifyDataSetChanged()
            ////////////////Genres

            bookmarkModel = BookmarkModel(
                bookmarkId = it.id.toLong(), name = it.title, runTime = time, genreList = genresString!!,
                ratting = it.vote_average.toString(), imageUrl = it.poster_path
            )
        }


        binding.movieDetailsBookmarkBTN.setOnClickListener {
            if (isBookmarked) {
                    isBookmarked = false
                    viewModel.deleteBookmark(ConstraintUtils.movieDetails.selectedMovieID.toLong())
                    Glide.with(requireActivity())
                        .load(R.drawable.bookmark_uncheck)
                        .into(binding.movieDetailsBookmarkBTN)
                    Toast.makeText(context,"Bookmark Removed",Toast.LENGTH_SHORT).show()
                } else {
                    isBookmarked = true
                    viewModel.insertBookmark(bookmarkModel)
                    Glide.with(requireActivity())
                        .load(R.drawable.bookmark_check)
                        .into(binding.movieDetailsBookmarkBTN)
                    Toast.makeText(context,"Bookmark Added",Toast.LENGTH_SHORT).show()
                }
        }

        return binding.root
    }

    private fun bookmarkedCompleted() {
        viewModel.getMovieById(ConstraintUtils.movieDetails.selectedMovieID.toLong())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    isBookmarked= true
                    Glide.with(requireActivity())
                        .load(R.drawable.bookmark_check)
                        .into(binding.movieDetailsBookmarkBTN)
                } else {
                    isBookmarked = false
                    Glide.with(requireActivity())
                        .load(R.drawable.bookmark_uncheck)
                        .into(binding.movieDetailsBookmarkBTN)
                }
            }
    }


}
