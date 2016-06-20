package com.example.codecentric.pma_movieapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codecentric.pma_movieapp.MainActivity;
import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.model.Series;
import com.example.codecentric.pma_movieapp.service.SeriesService;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SeriesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SERIES = "series";
    public static final String SERIE_TOAST = "You rate this serie with: ";

    private Series sSeries;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView average;
    TextView description;
    TextView firstAirDate;
    TextView seasons;
    RatingBar seriesRating;

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
        seriesRating = (RatingBar) findViewById(R.id.series_ratingBar);

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

        seriesRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                final double ratingValue = rating*2;

                Log.i("Vrednost ratinga je: ", String.valueOf(rating));

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://api.themoviedb.org/3")
                        .setRequestInterceptor(new RequestInterceptor() {
                            @Override
                            public void intercept(RequestFacade request) {
                                request.addEncodedQueryParam("api_key", "57ee1e7185a2f6b0600fb00374bc0515");
                                request.addEncodedQueryParam("session_id", "c6cb419443d23a5ca21eb34e6ba6722c2cff4b26");
                                request.addEncodedQueryParam("guest_session_id", "f9d8b7bab9e8281400ab8e869bb648b7");
                                request.addHeader("Content-Type","application/json");
                                request.addHeader("Accept","application/json");

                            }
                        })
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();

                JsonObject serieRating = new JsonObject();
                serieRating.addProperty("value",ratingValue);


                SeriesService service = restAdapter.create(SeriesService.class);
                service.rateSerie(sSeries.getId(), serieRating, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject s, Response response) {
                        Toast.makeText(getApplicationContext(), SERIE_TOAST + ratingValue ,Toast.LENGTH_LONG).show();
                        Log.d("Uspesno", s.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });

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