package net.aung.popularmovies.restapi;

import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.data.responses.MovieTrailerResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by aung on 12/15/15.
 */
public interface TheMovieApi {

    @GET("discover/movie")
    Call<MovieDiscoverResponse> discoverMovieList(
            @Query("api_key") String apiKey,
            @Query("page") int pageNumber,
            @Query("sort_by") String sortBy
    );

    @GET("movie/{movieId}videos")
    Call<MovieTrailerResponse> getTrailersByMovieId(
            @Query("api_key") String apiKey,
            @Query("movieId") int movieId
    );
}
