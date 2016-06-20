package com.example.codecentric.pma_movieapp.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.model.Movie;
import com.example.codecentric.pma_movieapp.service.MovieService;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";
    public static final String MOVIE_TOAST = "You rate this movie with: ";

    private Movie mMovie;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView average;
    TextView description;
    TextView releaseDate;
    RatingBar movieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(mMovie.getTitle());

        backdrop = (ImageView) findViewById(R.id.movie_backdrop);
        title = (TextView) findViewById(R.id.movie_title);
        average = (TextView) findViewById(R.id.movie_average);
        description = (TextView) findViewById(R.id.movie_description);
        poster = (ImageView) findViewById(R.id.movie_posters);
        releaseDate = (TextView) findViewById(R.id.movie_release_date);
        movieRating = (RatingBar) findViewById(R.id.movieRatingBar);

        title.setText(mMovie.getTitle());
        average.setText("Vote average: " + mMovie.getVoteAverage());
        description.setText(mMovie.getDescription());
        releaseDate.setText("Release date: " + mMovie.getReleaseDate());
        Picasso.with(this)
                .load(mMovie.getPoster())
                .into(poster);
        Picasso.with(this)
                .load(mMovie.getBackdrop())
                .into(backdrop);

        movieRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar,float rating, boolean fromUser) {

                final double ratingValue = rating*2;

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

                JsonObject movieRating = new JsonObject();
                movieRating.addProperty("value",ratingValue);

                MovieService service = restAdapter.create(MovieService.class);
                service.rateMovie(mMovie.getId(), movieRating, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject s, Response response) {
                        Toast.makeText(getApplicationContext(), MOVIE_TOAST + ratingValue,Toast.LENGTH_LONG).show();
                        Log.d("Uspesno", s.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
            }
        });


        Log.e("Movie objekat: ", mMovie.toString());

    }
}