package com.example.codecentric.pma_movieapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.adapters.SeriesAdapter;
import com.example.codecentric.pma_movieapp.model.Series;
import com.example.codecentric.pma_movieapp.service.SeriesService;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Aleksandar Ratkov on 24.5.16..
 */
public class SeriesFragment extends Fragment {


    private RecyclerView sRecyclerView;
    private SeriesAdapter sAdapter;

    public static SeriesFragment newInstance() {

        SeriesFragment ma = new SeriesFragment();

        return ma;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.series_fragment,container,false);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.search).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        //TODO dodati listener
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewSeries);
        sRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        sAdapter = new SeriesAdapter(getActivity());
        sRecyclerView.setAdapter(sAdapter);
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
        service.getPopularSeries(new Callback<Series.SeriesResult>() {
            @Override
            public void success(Series.SeriesResult seriesResult, Response response) {
                sAdapter.setSeriesList(seriesResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public SeriesViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_series);
        }
    }
}
