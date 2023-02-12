package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.app.adapter.NowShowingMovieAdapter
import com.muiedhossain.moviedbapi.app.adapter.PopularMovieAdapter
import com.muiedhossain.moviedbapi.app.viewModel.NowShowingMovieViewModel
import com.muiedhossain.moviedbapi.app.viewModel.PopularMovieViewModel
import com.muiedhossain.moviedbapi.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel_now : NowShowingMovieViewModel
    private lateinit var viewModel_popular : PopularMovieViewModel
    private lateinit var nowShowingMovieAdapter : NowShowingMovieAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel_now = ViewModelProvider(requireActivity()).get(NowShowingMovieViewModel::class.java)
        viewModel_popular = ViewModelProvider(requireActivity()).get(PopularMovieViewModel::class.java)

        nowShowingMovieAdapter = NowShowingMovieAdapter{ movie->
            findNavController().navigate(R.id.movieDetailsFragment)
        }
        popularMovieAdapter = PopularMovieAdapter{movie ->
            findNavController().navigate(R.id.movieDetailsFragment)
        }
        binding.horizontalRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            adapter = nowShowingMovieAdapter
        }
        binding.verticleRecyclerView.apply {
            layoutManager =  LinearLayoutManager(requireContext())
            adapter = popularMovieAdapter
        }

        viewModel_popular.getGenre()

        viewModel_now.getNowShowingMovie(1).observe(viewLifecycleOwner) {
            nowShowingMovieAdapter.submitList(it.results)
            Log.d("nowShowVIew", "onCreateView: "+it.results.toString())
        }
        viewModel_popular.getPopularMovie(1).observe(viewLifecycleOwner){
            popularMovieAdapter.submitList(it.results)
           // mProgressDialog.dismiss()
        }
        return binding.root
    }
}