package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.app.adapter.BookmarkAdapter
import com.muiedhossain.moviedbapi.app.adapter.PopularMovieAdapter
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.app.viewModel.BookmarkViewModel
import com.muiedhossain.moviedbapi.databinding.BookmarkItemBinding
import com.muiedhossain.moviedbapi.databinding.FragmentBookmarkBinding


class BookmarkFragment : Fragment() {

    private lateinit var fragment_binding: FragmentBookmarkBinding
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var bookmarkAdapter: BookmarkAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragment_binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(BookmarkViewModel::class.java)
        fragment_binding.toolbar.toolBarTV.text = "Bookmark"

        bookmarkAdapter = BookmarkAdapter{book,value ->
            if(value==1){
                Toast.makeText(context,"Bookmark Removed",Toast.LENGTH_SHORT).show()
                    viewModel.deleteBookmark(book.bookmarkId)
                }
            else{
                ConstraintUtils.movieDetails.selectedMovieID = book.bookmarkId.toInt()
                findNavController().navigate(R.id.movieDetailsFragment)
            }
        }

        fragment_binding.bookmarkRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookmarkAdapter
        }
        bookmarkAdapter.notifyDataSetChanged()

        viewModel.getBookmarkMovie().observe(viewLifecycleOwner){
            bookmarkAdapter.submitList(it)

        }
        return fragment_binding.root
    }
}