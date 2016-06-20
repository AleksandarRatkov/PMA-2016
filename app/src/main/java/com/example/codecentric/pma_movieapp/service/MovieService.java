package com.example.codecentric.pma_movieapp.service;

import com.example.codecentric.pma_movieapp.model.Genre;
import com.example.codecentric.pma_movieapp.model.Movie;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public interface MovieService {

    @GET("/movie/popular")
    void getPopularMovies(Callback<Movie.MovieResult> cb);


    @GET("/search/movie")
    void getSearchedMovies(Callback<Movie.MovieResult> cb);

    @GET("/genre/movie/list")
    void getMovieGenres(Callback<Genre.GenreResult> cb);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/movie/{movieId}/rating")
    void rateMovie(@Path("movieId") Long movieId, @Body JsonObject rating, Callback<JsonObject> cb);
}
