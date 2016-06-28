package com.example.codecentric.pma_movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.activities.SeasonDetailActivity;
import com.example.codecentric.pma_movieapp.fragments.SeasonFragment;
import com.example.codecentric.pma_movieapp.model.Season;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 15.6.16..
 */
public class SeasonAdapter extends RecyclerView.Adapter<SeasonFragment.SeasonViewHolder>{

    private List<Season> sSeasonList;
    private LayoutInflater sInflater;
    private Context sContext;

    public SeasonAdapter(Context context) {
        this.sContext = context;
        this.sInflater = LayoutInflater.from(context);
    }

    @Override
    public SeasonFragment.SeasonViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = sInflater.inflate(R.layout.row_season, parent, false);
        final SeasonFragment.SeasonViewHolder viewHolder = new SeasonFragment.SeasonViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(sContext, SeasonDetailActivity.class);
                intent.putExtra(SeasonDetailActivity.EXTRA_SEASON, sSeasonList.get(position));
                sContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeasonFragment.SeasonViewHolder holder, int position) {
        Season season = sSeasonList.get(position);
        Picasso.with(sContext)
                .load(season.getPoster())
                .placeholder(R.drawable.series)
                .noFade()
                .resize(400,400)
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return (sSeasonList == null) ? 0 : sSeasonList.size();
    }

    public void setSeasonList(List<Season> seasonList) {
        this.sSeasonList = new ArrayList<>();
        this.sSeasonList.addAll(seasonList);
        notifyDataSetChanged();
    }
}
