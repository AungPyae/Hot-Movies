package net.aung.popularmovies.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.aung.popularmovies.PopularMoviesApplication;
import net.aung.popularmovies.R;
import net.aung.popularmovies.adapters.TrailerListAdapter;
import net.aung.popularmovies.controllers.TrailerItemController;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.data.vos.TrailerVO;
import net.aung.popularmovies.databinding.FragmentMovieDetailBinding;
import net.aung.popularmovies.mvp.presenters.MovieDetailPresenter;
import net.aung.popularmovies.mvp.views.MovieDetailView;
import net.aung.popularmovies.views.components.recyclerview.TrailerItemDecoration;
import net.aung.popularmovies.views.pods.ViewPodMoviePopularity;
import net.aung.popularmovies.views.viewholders.TrailerViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aung on 12/15/15.
 */
public class MovieDetailFragment extends BaseFragment
        implements MovieDetailView,
        Palette.PaletteAsyncListener{

    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

    private int movieId;
    private FragmentMovieDetailBinding binding;
    private MovieDetailPresenter presenter;
    private Bitmap poster;
    private TrailerListAdapter trailerAdapter;
    private TrailerItemController controller;

    @Bind(R.id.vp_movie_popularity)
    ViewPodMoviePopularity vpMoviePopularity;

    @Bind(R.id.iv_poster)
    ImageView ivPoster;

    @Bind(R.id.ll_container_trailer)
    LinearLayout llContainerTrailer;

    @Bind(R.id.rv_trailers)
    RecyclerView rvTrailers;

    public static MovieDetailFragment newInstance(int movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_MOVIE_ID, movieId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controller = (TrailerItemController) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MovieDetailPresenter(this, movieId);
        presenter.onCreate();

        poster = PopularMoviesApplication.sPosterCache.get(0);
        Palette.from(poster).generate(this);

        trailerAdapter = TrailerListAdapter.newInstance(controller);
    }

    @Override
    protected void readArguments(Bundle bundle) {
        super.readArguments(bundle);
        movieId = bundle.getInt(ARG_MOVIE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, rootView);
        binding = DataBindingUtil.bind(rootView);

        ivPoster.setImageBitmap(poster);

        rvTrailers.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTrailers.setLayoutManager(layoutManager);
        rvTrailers.addItemDecoration(new TrailerItemDecoration(getContext()));

        rvTrailers.setAdapter(trailerAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void displayMovieDetail(MovieVO movie) {
        binding.setMovie(movie);
        vpMoviePopularity.drawPopularityIcons(movie.getPopularity());
    }

    @Override
    public void displayTrailerList(List<TrailerVO> trailerList) {
        trailerAdapter.setTrailerList(trailerList);
    }

    @Override
    public void onGenerated(Palette palette) {
        if (palette != null) {

            final Palette.Swatch darkVibrantSwatch    = palette.getDarkVibrantSwatch();
            final Palette.Swatch darkMutedSwatch      = palette.getDarkMutedSwatch();
            final Palette.Swatch lightVibrantSwatch   = palette.getLightVibrantSwatch();
            final Palette.Swatch lightMutedSwatch     = palette.getLightMutedSwatch();

            //-- start here.
            final Palette.Swatch vibrantSwatch        = palette.getVibrantSwatch();

            final Palette.Swatch colorDarkVaient = (darkVibrantSwatch != null)
                    ? darkVibrantSwatch : darkMutedSwatch;

            final Palette.Swatch colorLightVarient = (darkVibrantSwatch != null)
                    ? lightVibrantSwatch : lightMutedSwatch;

            setDarkVibrantColor(colorDarkVaient);
            //setLightVibrantColor(colorLightVarient);
            //setVibrantColor(vibrantSwatch);
        }
    }

    private void setDarkVibrantColor(Palette.Swatch swatch) {
        if(swatch != null){
            llContainerTrailer.setBackgroundColor(swatch.getRgb());
        }
    }
}
