package net.aung.popularmovies.data.model;

import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;

import net.aung.popularmovies.PopularMoviesApplication;
import net.aung.popularmovies.data.responses.MovieDiscoverResponse;
import net.aung.popularmovies.data.vos.MovieVO;
import net.aung.popularmovies.events.DataEvent;
import net.aung.popularmovies.restapi.MovieDataSource;
import net.aung.popularmovies.restapi.MovieDataSourceImpl;
import net.aung.popularmovies.restapi.RestApiConstants;
import net.aung.popularmovies.utils.CommonInstances;
import net.aung.popularmovies.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by aung on 12/12/15.
 */
public class MovieModel {

    public static final int INITIAL_PAGE_NUMBER = 1;

    private static final String DUMMY_MOVIE_LIST_FILE_NAME = "movie_discover.json";

    private static MovieModel objInstance = getInstance(); //active initiating.

    private SparseArray<ArrayList<MovieVO>> movieSparseArray;
    private MovieDataSource movieDataSource;

    public static MovieModel getInstance() {
        if (objInstance == null) {
            objInstance = new MovieModel();
        }

        return objInstance;
    }

    private MovieModel() {
        movieSparseArray = new SparseArray<>();
        movieDataSource = MovieDataSourceImpl.getInstance();

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }

    }

    //Async
    public List<MovieVO> loadMovieListByPage(int pageNumber, boolean isForce) {
        Log.d(PopularMoviesApplication.TAG, "loading new movie list for page " + pageNumber);
        List<MovieVO> movieList = movieSparseArray.get(pageNumber);
        if (movieList == null || isForce) {
            //new MovieLoaderTask(isForce).execute(pageNumber);
            movieDataSource.discoverMovieList(pageNumber, RestApiConstants.DEFAULT_SORT_BY, isForce);
        }

        return movieList;
    }

    public void onEventMainThread(DataEvent.LoadedMovieDiscoverEvent event) {
        boolean isForce = event.isForce();
        MovieDiscoverResponse response = event.getResponse();
        int currentPageNumber = response.getPage();

        ArrayList<MovieVO> loadedMovieList = response.getResults();
        if (isForce) {
            movieSparseArray.clear();
        }
        movieSparseArray.put(currentPageNumber, loadedMovieList);

        DataEvent.MovieListLoadedEvent movieListLoadedEvent = new DataEvent.MovieListLoadedEvent(loadedMovieList, currentPageNumber, event.isForce());
        EventBus.getDefault().post(movieListLoadedEvent);
    }

    private class MovieLoaderTask extends AsyncTask<Integer, Void, MovieDiscoverResponse> {

        private int pageNumber;
        private boolean isForce;

        public MovieLoaderTask(boolean isForce) {
            this.isForce = isForce;
        }

        @Override
        protected MovieDiscoverResponse doInBackground(Integer... params) {
            pageNumber = params[0];
            MovieDiscoverResponse response = null;
            try {
                String dummyMovieList = JsonUtils.getInstance().loadDummyData(DUMMY_MOVIE_LIST_FILE_NAME);
                response = CommonInstances.getGsonInstance().fromJson(dummyMovieList, MovieDiscoverResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(MovieDiscoverResponse response) {
            super.onPostExecute(response);
            movieSparseArray.put(pageNumber, response.getResults()); //put it into cache first.

            DataEvent.MovieListLoadedEvent event = new DataEvent.MovieListLoadedEvent(movieSparseArray.get(pageNumber), pageNumber, isForce);
            EventBus.getDefault().post(event);
        }
    }
}
