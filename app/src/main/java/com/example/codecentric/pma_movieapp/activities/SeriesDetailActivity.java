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

import com.example.codecentric.pma_movieapp.FragmentTransition;
import com.example.codecentric.pma_movieapp.MainActivity;
import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.fragments.SeasonFragment;
import com.example.codecentric.pma_movieapp.model.Series;
import com.squareup.picasso.Picasso;

public class SeriesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SERIES = "series";

    private Series sSeries;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView average;
    TextView description;
    TextView firstAirDate;
    TextView seasons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
        if (getIntent().hasExtra(EXTRA_SERIES)) {
            sSeries = getIntent().getParcelableExtra(EXTRA_SERIES);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a serie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(sSeries.getTitle());

        backdrop = (ImageView) findViewById(R.id.series_backdrop);
        title = (TextView) findViewById(R.id.series_title);
        average = (TextView) findViewById(R.id.series_average);
        description = (TextView) findViewById(R.id.series_description);
        poster = (ImageView) findViewById(R.id.series_poster);
        firstAirDate = (TextView) findViewById(R.id.series_firstairdate);
        seasons = (TextView) findViewById(R.id.series_seasons);

        title.setText(sSeries.getTitle());
        average.setText("Vote average: " + sSeries.getVoteAverage());
        description.setText(sSeries.getDescription());
        firstAirDate.setText("First Air Date: " + sSeries.getFirstAirDate());
        seasons.setText("See series seasons");

        seasons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SeriesDetailActivity.this,MainActivity.class);
                intent.putExtra("fragmentNumber",1);
                intent.putExtra("id",sSeries.getId());
                startActivity(intent);

            }
        });

        Picasso.with(this)
                .load(sSeries.getPoster())
                .into(poster);
        Picasso.with(this)
                .load(sSeries.getBackdrop())
                .into(backdrop);

        Log.e("Series objekat: ", sSeries.toString());



    }
}