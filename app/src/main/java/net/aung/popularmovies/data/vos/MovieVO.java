package net.aung.popularmovies.data.vos;

/**
 * Immutable.
 * Created by aung on 12/12/15.
 */
public class MovieVO {

    public static final String IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE_W185 = "w185";
    public static final String IMAGE_SIZE_W500 = "w500";

    private int id;
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int[] genre_ids;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private float popularity;
    private int vote_count;
    private boolean video;
    private float vote_average;

    public int getId() {
        return id;
    }

    public String getPoster_path() {
        return IMAGE_BASE_PATH + IMAGE_SIZE_W500 + poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public String getVote_average() {
        return String.format("%.1f", vote_average);
    }
}
