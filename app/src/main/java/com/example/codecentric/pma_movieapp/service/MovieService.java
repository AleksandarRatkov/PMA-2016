package com.example.codecentric.pma_movieapp.service;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public interface MovieService {

    @GET("/movie/popular")
    void getPopularMovies(Callback<com.example.codecentric.pma_movieapp.model.Movie.MovieResult> cb);


    @GET("/search/movie")
    void getSearchedMovies(Callback<com.example.codecentric.pma_movieapp.model.Movie.MovieResult> cb);

    @GET("/genre/movie/list")
    void getMovieGenres(Callback<com.example.codecentric.pma_movieapp.model.Genre.GenreResult> cb);
}
