package com.example.codecentric.pma_movieapp.service;

import com.example.codecentric.pma_movieapp.model.Actor;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Aleksandar Ratkov on 25.5.16..
 */
public interface ActorService {

    @GET("/person/popular")
    void getPopularActors(Callback<Actor.ActorResault> cb);


    @GET("/search/person")
    void getSearchedActors(Callback<Actor.ActorResault> cb);

}
