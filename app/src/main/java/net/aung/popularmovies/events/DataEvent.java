package net.aung.popularmovies.events;

import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.data.vos.MovieVO;

import java.util.List;

/**
 * Created by aung on 12/12/15.
 */
public class DataEvent {

    public static class MovieListLoadedEvent {

        private List<MovieVO> movieList;
        private int loadedPageNumber;

        public MovieListLoadedEvent(List<MovieVO> movieList, int loadedPageNumber) {
            this.movieList = movieList;
            this.loadedPageNumber = loadedPageNumber;
        }

        public List<MovieVO> getMovieList() {
            return movieList;
        }

        public int getLoadedPageNumber() {
            return loadedPageNumber;
        }
    }

    public static class LoadedMovieDiscoverEvent {
        private MovieDiscoverResponse response;

        public LoadedMovieDiscoverEvent(MovieDiscoverResponse response) {
            this.response = response;
        }

        public MovieDiscoverResponse getResponse() {
            return response;
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
}
