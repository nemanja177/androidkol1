package com.example.kolokvijum1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kolokvijum1.R;
import com.example.kolokvijum1.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> movieList;

    public MovieAdapter(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNaziv, tvOcena;
        public MovieViewHolder(View v) {
            super(v);
            tvNaziv = v.findViewById(R.id.tvNaziv);
            tvOcena = v.findViewById(R.id.tvOcena);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.tvNaziv.setText(movie.getNaziv());
        holder.tvOcena.setText("Ocena: " + movie.getOcena());
    }

    @Override
    public int getItemCount() { return movieList.size(); }
}
