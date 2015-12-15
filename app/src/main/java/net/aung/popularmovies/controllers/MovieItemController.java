package net.aung.popularmovies.controllers;

import net.aung.popularmovies.data.vos.MovieVO;

/**
 * Created by aung on 12/15/15.
 */
public interface MovieItemController extends BaseController {
    void onNavigateToDetail(MovieVO movie);
}
