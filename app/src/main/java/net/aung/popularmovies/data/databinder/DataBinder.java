package net.aung.popularmovies.data.databinder;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.aung.popularmovies.PopularMoviesApplication;
import net.aung.popularmovies.R;

import java.util.Random;

/**
 * Created by aung on 12/12/15.
 */
public final class DataBinder {

    public DataBinder() {

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView iv, String imageUrl) {
        Context context = iv.getContext();
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(iv);
    }
}
