package net.aung.popularmovies.data.vos;

/**
 * Immutable.
 * Created by aung on 12/12/15.
 */
public class MovieVO {

    private final int id;
    private final String poster_path;
    private final boolean adult;
    private final String overview;
    private final String release_date;
    private final int[] genre_ids;
    private final String original_title;
    private final String original_language;
    private final String title;
    private final String backdrop_path;
    private final float popularity;
    private final int vote_count;
    private final boolean video;
    private final float vote_average;

    //TODO consider to optimize the Constructor. Maybe Builder Pattern.
    public MovieVO(int id, String poster_path, boolean adult, String overview, String release_date, int[] genre_ids, String original_title, String original_language, String title, String backdrop_path, float popularity, int vote_count, boolean video, float vote_average) {
        this.id = id;
        this.poster_path = poster_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
        this.original_title = original_title;
        this.original_language = original_language;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
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

    public float getVote_average() {
        return vote_average;
    }
}
