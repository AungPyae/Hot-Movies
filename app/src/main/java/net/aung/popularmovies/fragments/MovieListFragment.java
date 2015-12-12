package net.aung.popularmovies.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.aung.popularmovies.R;
import net.aung.popularmovies.adapters.MovieListAdapter;
import net.aung.popularmovies.data.model.MovieModel;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.views.pods.ViewPodMoviePopularity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment {

    @Bind(R.id.rv_movies)
    RecyclerView rvMovies;

    private MovieListAdapter movieListAdapter;

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    public MovieListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<MovieVO> movieList = MovieModel.getInstance().loadDummyMovieList();
        movieListAdapter = MovieListAdapter.newInstance(movieList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, rootView);

        rvMovies.setAdapter(movieListAdapter);

        return rootView;
    }
}
