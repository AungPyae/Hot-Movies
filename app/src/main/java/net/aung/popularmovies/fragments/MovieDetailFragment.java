package net.aung.popularmovies.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.aung.popularmovies.R;
import net.aung.popularmovies.databinding.FragmentMovieDetailBinding;

import butterknife.ButterKnife;

/**
 * Created by aung on 12/15/15.
 */
public class MovieDetailFragment extends BaseFragment {

    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

    private int movieId;

    private FragmentMovieDetailBinding binding;

    public static MovieDetailFragment newInstance(int movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_MOVIE_ID, movieId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void readArguments(Bundle bundle) {
        super.readArguments(bundle);
        movieId = bundle.getInt(ARG_MOVIE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);

        return rootView;
    }
}
