package net.aung.popularmovies.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.aung.popularmovies.R;
import net.aung.popularmovies.adapters.MovieListAdapter;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.mvp.presenters.MovieListPresenter;
import net.aung.popularmovies.mvp.views.MovieListView;
import net.aung.popularmovies.views.components.recyclerview.SmartScrollListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment
        implements MovieListView,
        SwipeRefreshLayout.OnRefreshListener,
        SmartScrollListener.ControllerSmartScroll{

    @Bind(R.id.rv_movies)
    RecyclerView rvMovies;

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    private MovieListAdapter movieListAdapter;
    private MovieListPresenter movieListPresenter;
    private SmartScrollListener smartScrollListener;

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    public MovieListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieListAdapter = MovieListAdapter.newInstance();

        movieListPresenter = new MovieListPresenter(this);
        movieListPresenter.onCreate();

        smartScrollListener = new SmartScrollListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, rootView);

        rvMovies.setAdapter(movieListAdapter);
        rvMovies.addOnScrollListener(smartScrollListener);

        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        movieListPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        movieListPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
    }

    @Override
    public boolean isMovieListEmpty() {
        return movieListAdapter == null || movieListAdapter.getItemCount() == 0;
    }

    @Override
    public void displayMovieList(List<MovieVO> movieList, boolean isToAppend) {
        if (swipeContainer.isRefreshing()) {
            swipeContainer.setRefreshing(false);
        }

        if(isToAppend){
            movieListAdapter.appendMovieList(movieList);
        } else {
            movieListAdapter.setMovieList(movieList);
        }
    }


    @Override
    public void onRefresh() {
        movieListPresenter.forceRefresh();
    }

    @Override
    public void onListEndReached() {
        movieListPresenter.loadMoreData();
    }
}
