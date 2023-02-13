package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.viewModel.MovieDetailsViewModel
import com.muiedhossain.moviedbapi.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: FragmentMovieDetailsBinding
    private var genresList = ArrayList<Genre>()

    // private lateinit var genresAdapter: GenresAdapter
    private lateinit var bookmarks: BookmarkModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)

        viewModel.getMovieDetails().observe(viewLifecycleOwner) {

            var hour = it.runtime / 60
            var minute = it.runtime % 60
            var time = hour.toString() + "h " + minute.toString() + "min"

            bookmarks = BookmarkModel( bookmarkId = it.id.toLong(), name = it.title, runTime = time)


            binding.movieDetailsLengthTVID.text= "$hour h $minute min"

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500" + it.backdrop_path)
                .into(binding.movieDetailsImage)
            binding.movieDetailsTitle.text = it.title
            binding.movieDetailsStarRating.text = it.vote_average.toString()
            binding.movieDetailsLanguageTVID.text = it.original_language
            binding.singleMovieDetailsDescriptionTVID.text = it.overview

            }

        binding.movieDetailsBookmarkBTN.setOnClickListener {
            Log.e("bookmark2", "view1: bookmark Clicked" )
            viewModel.insertBookMarks(bookmarks)
            Log.e("bookmark3", "view2: "+viewModel.insertBookMarks(bookmarks) )

        }
        return binding.root
    }
    private fun checkBookmarked(id : Long) {
        viewModel.getMovieById(id).observe(viewLifecycleOwner){
            Log.e("bookMarkView", "checkFavorite: favorite00009"+it )
        }
    }
}