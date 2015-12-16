package net.aung.popularmovies.restapi;

import net.aung.popularmovies.BuildConfig;
import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.data.responses.MovieTrailerResponse;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.events.DataEvent;
import net.aung.popularmovies.utils.CommonInstances;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by aung on 12/15/15.
 */
public class MovieDataSourceImpl implements MovieDataSource {

    private static MovieDataSource objInstance;
    private final TheMovieApi theMovieApi;

    private MovieDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .build();

        theMovieApi = retrofit.create(TheMovieApi.class);
    }

    public static MovieDataSource getInstance() {
        if (objInstance == null) {
            objInstance = new MovieDataSourceImpl();
        }

        return objInstance;
    }

    @Override
    public void discoverMovieList(int pageNumber, String sortBy, final boolean isForce) {
        Call<MovieDiscoverResponse> movieDiscoverResponseCall = theMovieApi.discoverMovieList(
                BuildConfig.THE_MOVIE_API_KEY,
                pageNumber,
                sortBy
        );

        movieDiscoverResponseCall.enqueue(new MovieApiCallback<MovieDiscoverResponse>() {
            @Override
            public void onResponse(Response<MovieDiscoverResponse> response, Retrofit retrofit) {
                super.onResponse(response, retrofit);
                MovieDiscoverResponse movieDiscoverResponse = response.body();
                if (movieDiscoverResponse != null) {
                    DataEvent.LoadedMovieDiscoverEvent event = new DataEvent.LoadedMovieDiscoverEvent(movieDiscoverResponse, isForce);
                    EventBus.getDefault().post(event);
                }
            }
        });
    }

    @Override
    public void getMovieTrailers(final int movieId) {
        Call<MovieTrailerResponse> movieTrailerResponseCall = theMovieApi.getTrailersByMovieId(movieId, BuildConfig.THE_MOVIE_API_KEY);

        movieTrailerResponseCall.enqueue(new MovieApiCallback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Response<MovieTrailerResponse> response, Retrofit retrofit) {
                super.onResponse(response, retrofit);
                MovieTrailerResponse movieTrailerResponse = response.body();
                if (movieTrailerResponse != null) {
                    DataEvent.LoadedMovieTrailerEvent event = new DataEvent.LoadedMovieTrailerEvent(movieTrailerResponse, movieId);
                    EventBus.getDefault().post(event);
                }
            }
        });
    }

    @Override
    public void getMovieDetail(final int movieId) {
        Call<MovieVO> movieDetailCall = theMovieApi.getMovieDetailByMovieId(movieId, BuildConfig.THE_MOVIE_API_KEY);
        movieDetailCall.enqueue(new MovieApiCallback<MovieVO>() {
            @Override
            public void onResponse(Response<MovieVO> response, Retrofit retrofit) {
                super.onResponse(response, retrofit);
                MovieVO movieDetailResponse = response.body();
                if (movieDetailResponse != null) {
                    DataEvent.LoadedMovieDetailEvent event = new DataEvent.LoadedMovieDetailEvent(movieDetailResponse, movieId);
                    EventBus.getDefault().post(event);
                }
            }
        });
    }
}
