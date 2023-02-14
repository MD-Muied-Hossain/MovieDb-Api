package com.muiedhossain.moviedbapi.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.api.RetrofitInstance
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.model.PopularMovieModel
import com.muiedhossain.moviedbapi.app.repository.PopularMovieRepository
import kotlinx.coroutines.launch

class PopularMovieViewModel(application: Application)
    : AndroidViewModel(application) {
    private var repository: PopularMovieRepository
    private var api: ApiInterface = RetrofitInstance.getRetrofitInstance()
        .create(ApiInterface::class.java)
    private var dao : MovieDao
    init {
        api = RetrofitInstance.getRetrofitInstance()
            .create(ApiInterface::class.java)
        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        repository = PopularMovieRepository(api,dao,application)
    }

    val popularMovieResult : LiveData<PopularMovieModel>
        get() = repository.popularMovie

    fun getPopularMovie(page : Int) : LiveData<PopularMovieModel> {
        viewModelScope.launch {
            repository.getPopularMovie(page)
        }
        return repository.popularMovie
    }
    fun getGenre() {
        viewModelScope.launch {
            repository.getGenre()
        }
    }
    fun getGenreDataByID(id : Int) : LiveData<List<Genre>>{
        try {
            viewModelScope.launch {
                repository.getGenreDataByID(id)
            }
        }catch (e:Exception){}
        return repository.genreLiveData
    }

}