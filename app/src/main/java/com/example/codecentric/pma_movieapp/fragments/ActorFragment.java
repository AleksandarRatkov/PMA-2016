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
import android.view.inputmethod.InputMethodManager;
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

public class ActorFragment extends Fragment implements SearchView.OnQueryTextListener{


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
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);
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


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    private void getSearchedActors(final String query) {

        final String finalQuery = query.replace(" ","&");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "57ee1e7185a2f6b0600fb00374bc0515");
                        request.addEncodedQueryParam("query", finalQuery);
                        //TODO razmisliti da li da stavis da vraca samo 1 stranicu sa rezultatima
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        ActorService service = restAdapter.create(ActorService.class);
        service.getSearchedActors(new Callback<Actor.ActorResault>() {
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

    @Override
    public boolean onQueryTextSubmit(String query) {
            getSearchedActors(query);
            hideKeyboard(getActivity());
            return true;
        }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ActorViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_actor);
        }
    }
}
