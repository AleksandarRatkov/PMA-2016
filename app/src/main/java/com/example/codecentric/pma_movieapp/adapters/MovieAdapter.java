package com.example.codecentric.pma_movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.activities.MovieActivity;
import com.example.codecentric.pma_movieapp.activities.MovieDetailActivity;
import com.example.codecentric.pma_movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 24.5.16..
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieActivity.MovieViewHolder>{

    private List<Movie> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieActivity.MovieViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = mInflater.inflate(R.layout.row_movie, parent, false);
        final MovieActivity.MovieViewHolder viewHolder = new MovieActivity.MovieViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, mMovieList.get(position));
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieActivity.MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        Picasso.with(mContext)
                .load(movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.mMovieList = new ArrayList<>();
        this.mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }
}
