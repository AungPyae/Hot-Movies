package net.aung.popularmovies.data.responses;

import com.google.gson.annotations.SerializedName;

import net.aung.popularmovies.data.vos.TrailerVO;

import java.util.ArrayList;

/**
 * Created by aung on 12/16/15.
 */
public class MovieTrailerResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private ArrayList<TrailerVO> trailerList;

    public int getId() {
        return id;
    }

    public ArrayList<TrailerVO> getTrailerList() {
        return trailerList;
    }
}