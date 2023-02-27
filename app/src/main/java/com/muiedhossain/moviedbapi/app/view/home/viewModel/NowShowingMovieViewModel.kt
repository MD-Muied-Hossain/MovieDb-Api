package com.muiedhossain.moviedbapi.app.view.home.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.diffUtils.RetrofitInstance
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.view.home.model.NowShowingMovieModel
import com.muiedhossain.moviedbapi.app.view.home.repository.NowShowingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NowShowingMovieViewModel @Inject constructor (application: Application,private var repository: NowShowingRepository)
    : ViewModel(){
    private var api: ApiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
    private lateinit var dao : MovieDao

    init {
        api = RetrofitInstance.getRetrofitInstance()
            .create(ApiInterface::class.java)
        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        repository = NowShowingRepository(api)
    }

    val NowShowingMovieResult : LiveData<NowShowingMovieModel>
        get() = repository.nowShowingMovieResult

    fun getNowShowingMovie(page : Int) : LiveData<NowShowingMovieModel> {
        viewModelScope.launch {
            repository.getNowShowingMovie(page)
        }
        return  repository.nowShowingMovieResult
    }



}