package net.aung.popularmovies.restapi;

import net.aung.popularmovies.BuildConfig;
import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.events.DataEvent;
import net.aung.popularmovies.utils.CommonInstances;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
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

        movieDiscoverResponseCall.enqueue(new Callback<MovieDiscoverResponse>() {
            @Override
            public void onResponse(Response<MovieDiscoverResponse> response, Retrofit retrofit) {
                MovieDiscoverResponse movieDiscoverResponse = response.body();
                if (movieDiscoverResponse != null) {
                    DataEvent.LoadedMovieDiscoverEvent event = new DataEvent.LoadedMovieDiscoverEvent(movieDiscoverResponse, isForce);
                    EventBus.getDefault().post(event);
                } else {
                    DataEvent.FailedToLoadDataEvent event = new DataEvent.FailedToLoadDataEvent(response.message());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                DataEvent.FailedToLoadDataEvent event = new DataEvent.FailedToLoadDataEvent(throwable.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
