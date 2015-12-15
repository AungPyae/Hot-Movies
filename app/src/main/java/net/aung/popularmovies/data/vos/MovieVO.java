package net.aung.popularmovies.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Immutable.
 * Created by aung on 12/12/15.
 */
public class MovieVO {

    public static final String IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE_W185 = "w185";
    public static final String IMAGE_SIZE_W500 = "w500";

    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genre_ids")
    private int[] genreIds;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float voteAverage;

    private List<TrailerVO> trailerList;

    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return IMAGE_BASE_PATH + IMAGE_SIZE_W500 + posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return IMAGE_BASE_PATH + IMAGE_SIZE_W500 + backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public String getVoteAverage() {
        return String.format("%.1f", voteAverage);
    }

    public List<TrailerVO> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<TrailerVO> trailerList) {
        this.trailerList = trailerList;
    }
}
