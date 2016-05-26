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
import com.example.codecentric.pma_movieapp.adapters.ActorsAdapter;
import com.example.codecentric.pma_movieapp.model.Actor;
import com.example.codecentric.pma_movieapp.service.ActorService;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActorFragment extends Fragment {


    private RecyclerView aRecyclerView;
    private ActorsAdapter aAdapter;


    public static ActorFragment newInstance(){

        ActorFragment af = new ActorFragment();
        return af;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.actor_fragment,container,false);

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

        aRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewActor);
        aRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        aAdapter = new ActorsAdapter(getActivity());
        aRecyclerView.setAdapter(aAdapter);
        getPopularActors();
    }

    private void getPopularActors() {
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
        ActorService service = restAdapter.create(ActorService.class);
        service.getPopularActors(new Callback<Actor.ActorResault>() {
            @Override
            public void success(Actor.ActorResault actorResult, Response response) {
                aAdapter.setActorList(actorResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ActorViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_actor);
        }
    }
}
