package net.aung.popularmovies.mvp.presenters;

import net.aung.popularmovies.data.model.MovieModel;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.events.DataEvent;
import net.aung.popularmovies.mvp.views.MovieDetailView;

/**
 * Created by aung on 12/15/15.
 */
public class MovieDetailPresenter extends BasePresenter {

    private MovieDetailView movieDetailView;
    private MovieModel movieModel;
    private int movieId;

    public MovieDetailPresenter(MovieDetailView movieDetailView, int movieId) {
        this.movieDetailView = movieDetailView;
        movieModel = MovieModel.getInstance();
        this.movieId = movieId;
    }

    @Override
    public void onStart() {
        MovieVO movie = movieModel.loadMovieDetailByMovieId(movieId);
        if (movie != null) {
            movieDetailView.displayMovieDetail(movie);
        }

        movieModel.loadTrailerListByMovieId(movieId);
    }

    public void onEventMainThread(DataEvent.LoadedMovieTrailerEvent event) {
        movieDetailView.displayTrailerList(event.getResponse().getTrailerList());
    }

    @Override
    public void onStop() {

    }

    public void onEventMainThread(DataEvent.LoadedMovieDetailEvent event) {

    }
}
