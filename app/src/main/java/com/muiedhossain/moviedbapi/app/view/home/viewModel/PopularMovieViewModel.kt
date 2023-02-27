package com.muiedhossain.moviedbapi.app.view.home.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.diffUtils.RetrofitInstance
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.view.genres.Genre
import com.muiedhossain.moviedbapi.app.view.home.model.PopularMovieModel
import com.muiedhossain.moviedbapi.app.view.home.repository.PopularMovieRepository
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