package com.example.codecentric.pma_movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.activities.EpisodeDetailActivity;
import com.example.codecentric.pma_movieapp.fragments.EpisodeFragment;

import com.example.codecentric.pma_movieapp.model.Episode;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 14.6.16..
 */
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeFragment.EpisodeViewHolder>{

    private List<Episode> eEpisodesList;
    private LayoutInflater eInflater;
    private Context eContext;

    public EpisodeAdapter(Context context) {
        this.eContext = context;
        this.eInflater = LayoutInflater.from(context);
    }

    @Override
    public EpisodeFragment.EpisodeViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = eInflater.inflate(R.layout.row_episodes, parent, false);
        final EpisodeFragment.EpisodeViewHolder viewHolder = new EpisodeFragment.EpisodeViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(eContext, EpisodeDetailActivity.class);
                intent.putExtra(EpisodeDetailActivity.EXTRA_EPISODE, eEpisodesList.get(position));
                eContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EpisodeFragment.EpisodeViewHolder holder, int position) {
        Episode episodes = eEpisodesList.get(position);
        Picasso.with(eContext)
                .load(episodes.getPoster())
                .placeholder(R.drawable.series)
                .noFade()
                .resize(400,400)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (eEpisodesList == null) ? 0 : eEpisodesList.size();
    }

    public void setEpisodesList(List<Episode> episodesList) {
        this.eEpisodesList = new ArrayList<>();
        this.eEpisodesList.addAll(episodesList);
        notifyDataSetChanged();
    }
}
