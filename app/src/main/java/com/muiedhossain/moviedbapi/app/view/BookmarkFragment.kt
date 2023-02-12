package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.toolbar.appBarTV.text = "Bookmarks"

        viewModel.getBookMarkMovie().observe(viewLifecycleOwner) {
            for (i in 0..it.size - 1) {
                val splitingWithComma = it.get(i).genreList.split(",").toTypedArray()

            }
        }
        return binding.root
    }
}