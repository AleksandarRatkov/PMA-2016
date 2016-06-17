package com.example.codecentric.pma_movieapp.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codecentric.pma_movieapp.MainActivity;
import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.model.Season;
import com.example.codecentric.pma_movieapp.model.SeriesSeasonData;
import com.squareup.picasso.Picasso;

public class SeasonDetailActivity extends AppCompatActivity {

    public static final String EXTRA_SEASON = "season";

    private Season sSeason;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView airDate;
    TextView seasonNumber;
    TextView seasonEpisodes;
    TextView seasonDescription;
    TextView episodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_detail);
        if (getIntent().hasExtra(EXTRA_SEASON)) {
            sSeason = getIntent().getParcelableExtra(EXTRA_SEASON);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(sSeason.getTitle());

        backdrop = (ImageView) findViewById(R.id.season_backdrop);
        title = (TextView) findViewById(R.id.season_title);
        airDate = (TextView) findViewById(R.id.season_air_date);
        seasonNumber = (TextView) findViewById(R.id.season_number);
        poster = (ImageView) findViewById(R.id.season_poster);
        seasonEpisodes = (TextView) findViewById(R.id.season_episodes);
        seasonDescription = (TextView) findViewById(R.id.season_description);
        episodes = (TextView) findViewById(R.id.see_seasons_episodes);

        title.setText(sSeason.getTitle());
        airDate.setText("Air date: " + sSeason.getAirDate());
        seasonDescription.setText(sSeason.getDescription());
        seasonEpisodes.setText("Number of episodes: " + sSeason.getEpisodeCount());
        seasonNumber.setText("Season number: " + sSeason.getSeasonNumber());
        episodes.setText("See series seasons");

        episodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SeasonDetailActivity.this,MainActivity.class);
                intent.putExtra("fragmentNumber",2);
                intent.putExtra("seasonId",sSeason.getId());
                startActivity(intent);

            }
        });

        Picasso.with(this)
                .load(sSeason.getPoster())
                .into(poster);
        Picasso.with(this)
                .load(sSeason.getBackdropPath())
                .into(backdrop);


    }
}
