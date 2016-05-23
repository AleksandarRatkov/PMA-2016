package com.example.codecentric.pma_movieapp.service;

import com.example.codecentric.pma_movieapp.model.Series;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public interface SeriesService {

    @GET("/tv/popular")
    void getPopularMovies(Callback<Series.SeriesResult> cb);


    @GET("/search/tv")
    void getSearchedMovies(Callback<com.example.codecentric.pma_movieapp.model.Series.SeriesResult> cb);

    @GET("/genre/tv/list")
    void getMovieGenres(Callback<com.example.codecentric.pma_movieapp.model.Genre.GenreResult> cb);
}
