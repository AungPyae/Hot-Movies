package net.aung.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.aung.popularmovies.R;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.views.viewholders.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 12/12/15.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<MovieVO> movieList;

    public static MovieListAdapter newInstance() {
        List<MovieVO> movieList = new ArrayList<>();
        return newInstance(movieList);
    }

    public static MovieListAdapter newInstance(@NonNull List<MovieVO> movieList) {
        return new MovieListAdapter(movieList);
    }

    //Let's make sure movieList is never null. Ref: empty data pattern.
    private MovieListAdapter(@NonNull List<MovieVO> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View viMovie = inflater.inflate(R.layout.view_item_movie, parent, false);
        return new MovieViewHolder(viMovie);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieVO movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void appendMovieList(@NonNull List<MovieVO> newMovieList) {
        movieList.addAll(newMovieList);
        notifyDataSetChanged();
    }
}
