package com.example.codecentric.pma_movieapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.adapters.GenreAdapter;
import com.example.codecentric.pma_movieapp.model.Genre;
import com.example.codecentric.pma_movieapp.model.GenreData;
import com.example.codecentric.pma_movieapp.service.MovieService;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GenreFragment extends Fragment {

    private RecyclerView gRecyclerView;
    private GenreAdapter gAdapter;


    public static GenreFragment newInstance() {

        GenreFragment gf = new GenreFragment();

        return gf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.genre_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewGenre);
        gRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gAdapter = new GenreAdapter(getActivity());
        gRecyclerView.setAdapter(gAdapter);
        getMovieGenres();
    }

    private void getMovieGenres() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "57ee1e7185a2f6b0600fb00374bc0515");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MovieService service = restAdapter.create(MovieService.class);
        service.getMovieGenres(new Callback<GenreData>() {
            @Override
            public void success(GenreData genreResult, Response response) {
                gAdapter.setGenreList(genreResult.getGenres());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public GenreViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.row_genre);
        }
    }
}
