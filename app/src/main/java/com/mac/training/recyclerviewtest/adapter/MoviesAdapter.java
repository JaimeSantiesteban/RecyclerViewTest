package com.mac.training.recyclerviewtest.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac.training.recyclerviewtest.R;
import com.mac.training.recyclerviewtest.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 9/8/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, year, genre;
        public ImageView movieImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.year);
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
        }
    }

    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        Uri uri = Uri.parse(Movie.baseImageUrl + movie.getImageMovie());
        Context context = holder.movieImage.getContext();
        Picasso.with(context).load(uri)
                .into(holder.movieImage);

        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
