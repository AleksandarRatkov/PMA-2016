package com.example.codecentric.pma_movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.activities.SeriesDetailActivity;
import com.example.codecentric.pma_movieapp.fragments.SeriesFragment;
import com.example.codecentric.pma_movieapp.model.Series;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 24.5.16..
 */
public class SeriesAdapter extends RecyclerView.Adapter<SeriesFragment.SeriesViewHolder>{

    private List<Series> sSeriesList;
    private LayoutInflater sInflater;
    private Context sContext;

    public SeriesAdapter(Context context) {
        this.sContext = context;
        this.sInflater = LayoutInflater.from(context);
    }

    @Override
    public SeriesFragment.SeriesViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = sInflater.inflate(R.layout.row_series, parent, false);
        final SeriesFragment.SeriesViewHolder viewHolder = new SeriesFragment.SeriesViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(sContext, SeriesDetailActivity.class);
                intent.putExtra(SeriesDetailActivity.EXTRA_SERIES, sSeriesList.get(position));
                sContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeriesFragment.SeriesViewHolder holder, int position) {
        Series series = sSeriesList.get(position);
        Picasso.with(sContext)
                .load(series.getPoster())
                .placeholder(R.drawable.series)
                .noFade()
                .resize(400,400)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (sSeriesList == null) ? 0 : sSeriesList.size();
    }

    public void setSeriesList(List<Series> seriesList) {
        this.sSeriesList = new ArrayList<>();
        this.sSeriesList.addAll(seriesList);
        notifyDataSetChanged();
    }
}