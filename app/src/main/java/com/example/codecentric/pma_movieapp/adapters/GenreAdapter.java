package com.example.codecentric.pma_movieapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codecentric.pma_movieapp.FragmentTransition;
import com.example.codecentric.pma_movieapp.R;
import com.example.codecentric.pma_movieapp.fragments.GenreFragment;
import com.example.codecentric.pma_movieapp.fragments.MovieFragment;
import com.example.codecentric.pma_movieapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 21.6.16..
 */
public class GenreAdapter  extends RecyclerView.Adapter<GenreFragment.GenreViewHolder> {

    public static String GENRE_ID = "genreId";

    private List<Genre> gGenreList;
    private LayoutInflater gInflater;
    private Context gContext;
    private MovieAdapter mAdapter;


    public GenreAdapter(Context context) {
        this.gContext = context;
        this.gInflater = LayoutInflater.from(context);
    }

    @Override
    public GenreFragment.GenreViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = gInflater.inflate(R.layout.row_genre, parent, false);
        final GenreFragment.GenreViewHolder viewHolder = new GenreFragment.GenreViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Bundle bundle = new Bundle();
                bundle.putLong(GENRE_ID, gGenreList.get(position).getId() );
                
                MovieFragment movieFragment = new MovieFragment();
                movieFragment.setArguments(bundle);
                FragmentTransition.to(movieFragment, (FragmentActivity) gContext, false);

            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(GenreFragment.GenreViewHolder holder, int position) {
        Genre genre = gGenreList.get(position);
        holder.name.setText( position+1 + ". " + genre.getName() + "\n");
    }


    @Override
    public int getItemCount() {
        return (gGenreList == null) ? 0 : gGenreList.size();
    }

    public void setGenreList(List<Genre> genreList) {
        this.gGenreList = new ArrayList<>();
        this.gGenreList.addAll(genreList);
        notifyDataSetChanged();
    }
}
