package com.example.codecentric.pma_movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.activities.ActorDetailActivity;
import com.example.codecentric.pma_movieapp.fragments.ActorFragment;
import com.example.codecentric.pma_movieapp.model.Actor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 25.5.16..
 */
public class ActorsAdapter extends RecyclerView.Adapter<ActorFragment.ActorViewHolder>{

    private List<Actor> aActorsList;
    private LayoutInflater aInflater;
    private Context aContext;

    public ActorsAdapter(Context context) {
        this.aContext = context;
        this.aInflater = LayoutInflater.from(context);
    }

    @Override
    public ActorFragment.ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = aInflater.inflate(R.layout.row_actor,parent,false);
        final ActorFragment.ActorViewHolder viewHolder = new ActorFragment.ActorViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Intent i = new Intent(aContext, ActorDetailActivity.class);
                i.putExtra(ActorDetailActivity.EXTRA_ACTOR, aActorsList.get(position));
                aContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActorFragment.ActorViewHolder holder, int position) {
        Actor actor = aActorsList.get(position);
        Picasso.with(aContext)
                .load(actor.getProfilePath())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (aActorsList == null) ? 0 : aActorsList.size();
    }

    public void setActorList(List<Actor> actorList) {
        this.aActorsList = new ArrayList<>();
        this.aActorsList.addAll(actorList);
        notifyDataSetChanged();
    }
}
