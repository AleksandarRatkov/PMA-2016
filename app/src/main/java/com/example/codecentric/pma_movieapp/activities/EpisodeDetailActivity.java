package com.example.codecentric.pma_movieapp.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.model.Episode;
import com.example.codecentric.pma_movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class EpisodeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_EPISODE = "episode";

    private Episode eEpisode;
    ImageView poster;
    ImageView seasonPoster;
    TextView name;
    TextView episodeNumber;
    TextView description;
    TextView releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);
        if (getIntent().hasExtra(EXTRA_EPISODE)) {
            eEpisode = getIntent().getParcelableExtra(EXTRA_EPISODE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(eEpisode.getName());


        name = (TextView) findViewById(R.id.episode_name);
        episodeNumber = (TextView) findViewById(R.id.episode_number);
        description = (TextView) findViewById(R.id.episode_description);
        seasonPoster = (ImageView) findViewById(R.id.season_of_episode_poster);
        poster = (ImageView) findViewById(R.id.episode_poster);
        releaseDate = (TextView) findViewById(R.id.episode_release_date);

        name.setText(eEpisode.getName());
        episodeNumber.setText("Episode : " + eEpisode.getVoteAverage());
        description.setText(eEpisode.getDescription());
        releaseDate.setText("Release date: " + eEpisode.getAirDate());
        Picasso.with(this)
                .load(eEpisode.getSeasonPoster())
                .into(seasonPoster);

        Picasso.with(this)
                .load(eEpisode.getPoster())
                .into(poster);

        Log.e("Episode objekat: ", eEpisode.toString());

    }
}
