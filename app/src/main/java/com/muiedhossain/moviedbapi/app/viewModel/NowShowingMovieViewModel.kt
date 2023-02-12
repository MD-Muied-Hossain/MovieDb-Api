package com.muiedhossain.moviedbapi.app.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.api.RetrofitInstance
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.model.NowShowingMovieModel
import com.muiedhossain.moviedbapi.app.model.PopularMovieModel
import com.muiedhossain.moviedbapi.app.repository.NowShowingRepository
import kotlinx.coroutines.launch

class NowShowingMovieViewModel(application: Application)
    : AndroidViewModel(application){
    private lateinit var repository: NowShowingRepository
    private var api: ApiInterface = RetrofitInstance.getRetrofitInstance()
        .create(ApiInterface::class.java)
    private lateinit var dao : MovieDao

    init {
        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        repository = NowShowingRepository(api,dao)
    }
    fun getNowShowingMovie(page : Int) : LiveData<NowShowingMovieModel> {
        viewModelScope.launch {
            repository.getNowShowingMovie(page)
        }
        return  repository.movieResult
    }



}