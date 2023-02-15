package com.muiedhossain.moviedbapi.app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.app.adapter.NowShowingMovieAdapter
import com.muiedhossain.moviedbapi.app.adapter.PopularMovieAdapter
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.model.PopularMovieResult
import com.muiedhossain.moviedbapi.app.viewModel.NowShowingMovieViewModel
import com.muiedhossain.moviedbapi.app.viewModel.PopularMovieViewModel
import com.muiedhossain.moviedbapi.app.model.Result
import com.muiedhossain.moviedbapi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel_now: NowShowingMovieViewModel
    private lateinit var viewModel_popular: PopularMovieViewModel
    private lateinit var nowShowingMovieAdapter: NowShowingMovieAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    var isNowLoading = false
    var isPopularLoading = false
    var nowShowingVisibleItems = 0
    var nowShowingFullyVisibleItems = 0
    var nowShowingTotalItemsViews = 0
    var nowShowingPage = 1
    var popularMoviePage = 1
    var popularVisibleItems = 0
    var popularFullyVisibleItems = 0
    var popularTotalItemsViews = 0
    var nowShowinglist = ArrayList<Result>()
    var popularList = ArrayList<PopularMovieResult>()
    var genreList = ArrayList<Genre>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        /////////////////////0
        viewModel_now = ViewModelProvider(requireActivity()).get(NowShowingMovieViewModel::class.java)
        viewModel_popular = ViewModelProvider(requireActivity()).get(PopularMovieViewModel::class.java)
        /////////////////////0

        //////////////////////////////1
        nowShowingMovieAdapter = NowShowingMovieAdapter { movie ->
            findNavController().navigate(R.id.movieDetailsFragment)
        }
        popularMovieAdapter = PopularMovieAdapter { movie, binding, value ->
            if (value == 2) {
                for (i in 0 until movie.genre_ids.size) {
                    var genre_ids = movie.genre_ids.get(i)
                    viewModel_popular.getGenreDataByID(genre_ids).observe(viewLifecycleOwner) {
                        for (i in 0..it.size - 1) {
                            it.forEach {
                                if (genre_ids == it.id) {
                                    val dynamicTextView = TextView(requireContext())
                                    dynamicTextView.text = it.name
                                    dynamicTextView.setBackgroundResource(R.drawable.genre_background)
                                    val params = LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                    )
                                    params.setMargins(0, 10, 15, 5)

                                    dynamicTextView.layoutParams = params
                                    binding.popularGanerRVID.addView(dynamicTextView)
                                }

                            }
                        }

                    }
                }
            } else if (value == 1) {
                Log.e("callback", "onCreateView: insert")
                findNavController().navigate(R.id.movieDetailsFragment)
            }
        }
        //////////////////////////////1

        /////////////2
        fatchNowShowingMovieData(nowShowingPage)
        fatchPopularMovieData(popularMoviePage)
        /////////////2

        /////////////////////////////3
        val nowShowingLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val popularLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        ////////////////////////////3

        ////////////////////////////4
        binding.horizontalRecyclerview.apply {
            layoutManager = nowShowingLayoutManager
            adapter = nowShowingMovieAdapter
        }
        binding.verticleRecyclerView.apply {
            layoutManager = popularLayoutManager
            adapter = popularMovieAdapter
        }
        ////////////////////////////4

        ///////////////////////////5
        binding.horizontalRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isNowLoading = true
                }

            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                nowShowingVisibleItems = nowShowingLayoutManager.childCount
                nowShowingTotalItemsViews = nowShowingLayoutManager.itemCount
                nowShowingFullyVisibleItems = nowShowingLayoutManager.findFirstVisibleItemPosition()

                if(isNowLoading && (nowShowingVisibleItems + nowShowingFullyVisibleItems >= nowShowingTotalItemsViews)){
                    isNowLoading = false
                    nowShowingPage++
                    fatchNowShowingMovieData(nowShowingPage)
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.verticleRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if( newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL ){
                    isNowLoading = true
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                popularVisibleItems = popularLayoutManager.childCount
                popularFullyVisibleItems = popularLayoutManager.findFirstVisibleItemPosition()
                popularTotalItemsViews = popularLayoutManager.itemCount

                if( isNowLoading && ( popularVisibleItems + popularFullyVisibleItems >= popularTotalItemsViews ) ){
                    isPopularLoading = false
                    popularMoviePage++
                    fatchPopularMovieData( popularMoviePage )
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        })
        ///////////////////////////5

        viewModel_popular.getGenre()


        //////////////////////////need to be upgrade
        /*viewModel_now.getNowShowingMovie(nowShowingPage).observe(viewLifecycleOwner) {
            nowShowingMovieAdapter.submitList(it.results)
            Log.d("nowShowVIew", "onCreateView: " + it.results.toString())
        }
        viewModel_popular.getPopularMovie(popularMoviePage).observe(viewLifecycleOwner) {
            popularMovieAdapter.submitList(it.results)
            // mProgressDialog.dismiss()
        }*/
        //////////////////////////need to be upgrade

        /////////////////////////6
        viewModel_popular.popularMovieResult.observe(viewLifecycleOwner) {

            for (i in 0 until it.results.size -1) {
                popularList.add(it.results[i])
            }
            popularMovieAdapter.submitList(popularList)
            popularMovieAdapter.notifyDataSetChanged()

            //mProgressDialog.dismiss()

        }
        viewModel_now.NowShowingMovieResult.observe(viewLifecycleOwner) {

            for (i in 0 until it.results.size -1 ) {
                nowShowinglist.add(it.results[i])
            }
            nowShowingMovieAdapter.submitList(nowShowinglist)
            nowShowingMovieAdapter.notifyDataSetChanged()
        }
        ////////////////////////6

        return binding.root
    }

    private fun fatchPopularMovieData(popularMoviePage: Int) {
        Log.e("popularMoviePage", "fatchPopularMovieData: `"+popularMoviePage )
        viewModel_popular.getPopularMovie(popularMoviePage)
    }

    private fun fatchNowShowingMovieData(nowShowingPage: Int) {
        Log.e("nowShowingPage", "fatchPopularMovieData: `"+nowShowingPage )
        viewModel_now.getNowShowingMovie(nowShowingPage)
    }
}