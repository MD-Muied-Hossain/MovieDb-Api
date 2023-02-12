package com.muiedhossain.moviedbapi.app.api

import com.muiedhossain.moviedbapi.app.model.GenreModel
import com.muiedhossain.moviedbapi.app.model.MovieDetailsModel
import com.muiedhossain.moviedbapi.app.model.NowShowingMovieModel
import com.muiedhossain.moviedbapi.app.model.PopularMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


////api_key = 671030f7bacb09cb3d0914df622394d9

interface ApiInterface {

    @GET("3/movie/now_playing?api_key=671030f7bacb09cb3d0914df622394d9&language=en-US")
    suspend fun getNowShowingMovie(@Header("page") page : Int) : Response<NowShowingMovieModel>

    @GET("3/movie/popular?api_key=671030f7bacb09cb3d0914df622394d9&language=en-US")
    suspend fun getPopularMovie(@Header("page") page : Int) : Response<PopularMovieModel>

    @GET("3/genre/movie/list?api_key=671030f7bacb09cb3d0914df622394d9&language=en-US")
    suspend fun getGenre() : Response<GenreModel>

    @GET("3/movie/{movie_id}?api_key=671030f7bacb09cb3d0914df622394d9&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id")  movieId : Int) : Response<MovieDetailsModel>

}