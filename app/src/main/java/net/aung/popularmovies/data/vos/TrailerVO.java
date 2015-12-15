package net.aung.popularmovies.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/16/15.
 */
public class TrailerVO {

    @SerializedName("id")
    private String id;

    @SerializedName("iso_639_1")
    private String iso639_1;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("site")
    private String site;

    @SerializedName("size")
    private int size;

    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
