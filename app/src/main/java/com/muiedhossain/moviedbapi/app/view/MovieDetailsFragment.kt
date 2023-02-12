package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.viewModel.MovieDetailsViewModel
import com.muiedhossain.moviedbapi.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding : FragmentMovieDetailsBinding
    private var genresList = ArrayList<Genre>()
   // private lateinit var genresAdapter: GenresAdapter
    private lateinit var bookmarks : BookmarkModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }
}