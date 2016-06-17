package com.example.codecentric.pma_movieapp.fragments;




import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.adapters.SeasonAdapter;
import com.example.codecentric.pma_movieapp.model.Season;
import com.example.codecentric.pma_movieapp.model.SeriesSeasonData;
import com.example.codecentric.pma_movieapp.service.SeriesService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SeasonFragment extends Fragment {


    private RecyclerView sRecyclerView;
    private SeasonAdapter sAdapter;
    private Long seriesId;


    public static SeasonFragment newInstance() {

        SeasonFragment sf = new SeasonFragment();

        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getActivity().getIntent().getExtras();
        seriesId = bundle.getLong("id");

        View view = inflater.inflate(R.layout.season_fragment,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewSeason);
        sRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        sAdapter = new SeasonAdapter(getActivity());
        sRecyclerView.setAdapter(sAdapter);
        getSeasons();
    }

    private void getSeasons() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                        //request.addPathParam("id",seriesId.toString());
                        request.addEncodedQueryParam("api_key", "57ee1e7185a2f6b0600fb00374bc0515");

                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        SeriesService service = restAdapter.create(SeriesService.class);
        service.getSeriesSeasons(seriesId, new Callback<SeriesSeasonData>() {
            @Override
            public void success(SeriesSeasonData seasonResult, Response response) {

                List<Season> seasons = new ArrayList<>();

                for (Season s: seasonResult.getSeasons()) {

                    s.setTitle(seasonResult.getTitle());
                    s.setBackdropPath(seasonResult.getBackdropPath());
                    s.setDescription(seasonResult.getDescription());
                    s.setSeriesId(seasonResult.getId());
                    seasons.add(s);
                }
                sAdapter.setSeasonList(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public static class SeasonViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public SeasonViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_season);
        }
    }
}
