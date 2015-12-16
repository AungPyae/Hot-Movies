package net.aung.popularmovies.events;

import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.data.responses.MovieTrailerResponse;
import net.aung.popularmovies.data.vos.MovieVO;

import java.util.List;

/**
 * Created by aung on 12/12/15.
 */
public class DataEvent {

    public static class LoadedMovieDiscoverEvent {
        private MovieDiscoverResponse response;
        private boolean isForce;

        public LoadedMovieDiscoverEvent(MovieDiscoverResponse response, boolean isForce) {
            this.response = response;
            this.isForce = isForce;
        }

        public MovieDiscoverResponse getResponse() {
            return response;
        }

        public boolean isForce() {
            return isForce;
        }
    }

    public static class FailedToLoadDataEvent {
        private String message;

        public FailedToLoadDataEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class LoadedMovieDetailEvent {
        private MovieVO movie;
        private int movieId;

        public LoadedMovieDetailEvent(MovieVO movie, int movieId) {
            this.movie = movie;
            this.movieId = movieId;
        }

        public MovieVO getMovie() {
            return movie;
        }

        public int getMovieId() {
            return movieId;
        }
    }

    public static class LoadedMovieTrailerEvent {
        private MovieTrailerResponse response;
        private int movieId;

        public LoadedMovieTrailerEvent(MovieTrailerResponse response, int movieId) {
            this.response = response;
            this.movieId = movieId;
        }

        public MovieTrailerResponse getResponse() {
            return response;
        }

        public int getMovieId() {
            return movieId;
        }
    }
}
