package com.example.movieapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<MovieModel> movies;

    public MovieListAdapter(List<MovieModel> movies) {
        this.movies = movies;
    }

    public void updateMovies(List<MovieModel> newMovieList) {
        movies.clear();
        movies.addAll(newMovieList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView movieImage;

        @BindView(R.id.movieTitle)
        TextView movieName;

        @BindView(R.id.movieRating)
        TextView movieRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        void bind(MovieModel movie) {
        movieName.setText(movie.getTitle());
        movieRating.setText(String.valueOf(movie.getVoteScore()));

        }


    }

}
