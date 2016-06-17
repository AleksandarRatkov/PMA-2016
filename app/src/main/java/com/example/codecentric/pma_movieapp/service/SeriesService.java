package com.example.codecentric.pma_movieapp.service;

import com.example.codecentric.pma_movieapp.model.Episode;
import com.example.codecentric.pma_movieapp.model.EpisodeData;
import com.example.codecentric.pma_movieapp.model.Genre;
import com.example.codecentric.pma_movieapp.model.SeriesSeasonData;
import com.example.codecentric.pma_movieapp.model.Series;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public interface SeriesService {

    @GET("/tv/popular")
    void getPopularSeries(Callback<Series.SeriesResult> cb);


    @GET("/search/tv")
    void getSearchedSeries(Callback<Series.SeriesResult> cb);

    @GET("/tv/{id}")
    void getSeriesSeasons(@Path("id") Long seriesId, Callback<SeriesSeasonData> cb);

    @GET("/tv/{seriesId}/season/{seasonNumber}")
    void getSeasonsEpisodes(@Path("seriesId") Long seriesId,@Path("seasonNumber") int seasonNumber, Callback<EpisodeData> cb);

    @GET("/genre/tv/list")
    void getSeriesGenres(Callback<Genre.GenreResult> cb);
}
