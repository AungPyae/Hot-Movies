package net.aung.popularmovies.mvp.views;

import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.data.vos.TrailerVO;
import net.aung.popularmovies.views.viewholders.TrailerViewHolder;

import java.util.List;

/**
 * Created by aung on 12/15/15.
 */
public interface MovieDetailView {
    void displayMovieDetail(MovieVO movie);
    void displayTrailerList(List<TrailerVO> trailerList);
}
