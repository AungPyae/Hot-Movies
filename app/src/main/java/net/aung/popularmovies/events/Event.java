package net.aung.popularmovies.events;

import net.aung.popularmovies.data.vos.MovieVO;

import java.util.List;

/**
 * Created by aung on 12/12/15.
 */
public class Event {

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
}
