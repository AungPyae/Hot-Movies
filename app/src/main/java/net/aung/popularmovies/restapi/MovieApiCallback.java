package net.aung.popularmovies.restapi;

import net.aung.popularmovies.events.DataEvent;

import de.greenrobot.event.EventBus;
import retrofit.Callback;

/**
 * Created by aung on 12/16/15.
 */
public abstract class MovieApiCallback<T> implements Callback<T> {

    @Override
    public void onFailure(Throwable throwable) {
        DataEvent.FailedToLoadDataEvent event = new DataEvent.FailedToLoadDataEvent(throwable.getMessage());
        EventBus.getDefault().post(event);
    }
}
