package net.aung.popularmovies.events;

import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.data.responses.MovieTrailerResponse;
import net.aung.popularmovies.data.vos.MovieVO;

import java.util.List;

/**
 * Created by aung on 12/12/15.
 */
public class DataEvent {

    public static class MovieListLoadedEvent {

        private List<MovieVO> movieList;
        private int loadedPageNumber;
        private boolean isForce;

        public MovieListLoadedEvent(List<MovieVO> movieList, int loadedPageNumber, boolean isForce) {
            this.movieList = movieList;
            this.loadedPageNumber = loadedPageNumber;
            this.isForce = isForce;
        }

        public List<MovieVO> getMovieList() {
            return movieList;
        }

        public int getLoadedPageNumber() {
            return loadedPageNumber;
        }

        public boolean isForce() {
            return isForce;
        }
    }

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

    }

    public static class LoadedMovieTrailerEvent {
        private MovieTrailerResponse response;

        public LoadedMovieTrailerEvent(MovieTrailerResponse response) {
            this.response = response;
        }

        public MovieTrailerResponse getResponse() {
            return response;
        }
    }
}
