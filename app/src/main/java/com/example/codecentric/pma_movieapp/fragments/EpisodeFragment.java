package com.example.codecentric.pma_movieapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.codecentric.pma_movieapp.MainActivity;
import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.adapters.EpisodeAdapter;
import com.example.codecentric.pma_movieapp.model.Episode;
import com.example.codecentric.pma_movieapp.model.EpisodeData;
import com.example.codecentric.pma_movieapp.model.Season;
import com.example.codecentric.pma_movieapp.service.SeriesService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Aleksandar Ratkov on 14.6.16..
 */
public class EpisodeFragment extends Fragment{


    private RecyclerView eRecyclerView;
    private EpisodeAdapter eAdapter;
    private Long seriesId;
    private int seasonNumber;

    public static EpisodeFragment newInstance() {

        EpisodeFragment ma = new EpisodeFragment();

        return ma;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getActivity().getIntent().getExtras();
        seriesId = bundle.getLong(MainActivity.SERIES_ID);
        seasonNumber = bundle.getInt(MainActivity.SEASON_NUMBER);

        View view = inflater.inflate(R.layout.episodes_fragment,container,false);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        eRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewEpisodes);
        eRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        eAdapter = new EpisodeAdapter(getActivity());
        eRecyclerView.setAdapter(eAdapter);
        getPopularSeries();
    }

    private void getPopularSeries() {
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
        SeriesService service = restAdapter.create(SeriesService.class);
        service.getSeasonsEpisodes(seriesId,seasonNumber,new Callback<EpisodeData>() {
            @Override
            public void success(EpisodeData episodeResult, Response response) {
                List<Episode> episodes = new ArrayList<>();

                for (Episode e: episodeResult.getEpisodes()) {

                    e.setSeasonPoster(episodeResult.getPoster());
                    episodes.add(e);
                }

                eAdapter.setEpisodesList(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public EpisodeViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_episodes);
        }
    }
}
