package net.aung.popularmovies.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/16/15.
 */
public class ProductionCompanyVO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
