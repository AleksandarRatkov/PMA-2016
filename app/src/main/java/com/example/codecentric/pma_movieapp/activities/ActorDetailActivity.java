package com.example.codecentric.pma_movieapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.model.Actor;
import com.example.codecentric.pma_movieapp.model.Movie;
import com.example.codecentric.pma_movieapp.model.Series;
import com.squareup.picasso.Picasso;

public class ActorDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ACTOR = "actor";

    private Actor aActor;
    ImageView profilePath;
    TextView name;
    TextView popularity;
    TextView movie1;
    TextView movie2;
    TextView movie3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        if (getIntent().hasExtra(EXTRA_ACTOR)) {
            aActor = getIntent().getParcelableExtra(EXTRA_ACTOR);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(aActor.getName());

        profilePath = (ImageView) findViewById(R.id.actor_profile_path);
        name = (TextView) findViewById(R.id.actor_name);
        popularity = (TextView) findViewById(R.id.actor_popularity);
        movie1 = (TextView) findViewById(R.id.movie1);
        movie2 = (TextView) findViewById(R.id.movie2);
        movie3 = (TextView) findViewById(R.id.movie3);

        name.setText(aActor.getName());
        popularity.setText( "Actors popularity:  " + aActor.getPopularity());
        Picasso.with(this)
                .load(aActor.getProfilePath())
                .into(profilePath);

        movie1.setText("1. " + titleBasedOnType(aActor.getKnownFor().get(0)));
        movie2.setText("2. " + titleBasedOnType(aActor.getKnownFor().get(1)));
        movie3.setText("3. " + titleBasedOnType(aActor.getKnownFor().get(2)));

        movie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(aActor.getKnownFor().get(0));
            }
        });

        movie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIntent(aActor.getKnownFor().get(1));
            }
        });

        movie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIntent(aActor.getKnownFor().get(2));
            }
        });

        Log.e("Actor objekat: ", aActor.toString());
    }

    private void setIntent(Movie m) {
        Intent intent;
        if(isMovie(m)){

            intent = new Intent( ActorDetailActivity.this , MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, m);
        }else{

            intent = new Intent( ActorDetailActivity.this , SeriesDetailActivity.class);
            intent.putExtra(SeriesDetailActivity.EXTRA_SERIES, castMovieToSeries(m));
        }


        startActivity(intent);
    }

    /*
      Vratice naziv serije ili filma zbog ispisa-da ne bude null
    */

    public String titleBasedOnType(Movie m){

        return ( m.getTitle() == null ) ? m.getTitleS():m.getTitle();
    }

    public boolean isMovie(Movie m){

        return ( m.getTitle() == null ) ? false:true;
    }

    public Series castMovieToSeries(Movie m){

        return new Series(m.getTitleS(),m.getDescription(),m.getPoster(),m.getBackdrop(),m.getVoteAverage(),m.getFirstAirDate());
    }
}
