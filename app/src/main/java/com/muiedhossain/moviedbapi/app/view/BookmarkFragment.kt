package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muiedhossain.moviedbapi.app.viewModel.BookmarkViewModel
import com.muiedhossain.moviedbapi.databinding.FragmentBookmarkBinding


class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var viewModel: BookmarkViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(BookmarkViewModel::class.java)
        binding.toolbar.appBarTV.text = "Bookmark"

        viewModel.getBookMarkMovie().observe(viewLifecycleOwner) {
            Log.d("bookmoovie", "onCreateView: "+viewModel.getBookMarkMovie())

        }
        return binding.root
    }
}