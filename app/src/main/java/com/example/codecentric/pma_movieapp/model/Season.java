package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by codecentric on 16.6.16..
 */
public class Season implements Parcelable{

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private Long id;

    @SerializedName("season_number")
    private int seasonNumber;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("episode_count")
    private int episodeCount;

    private String backdropPath;

    private String title;

    private String description;

    public Season(Long id, int seasonNumber, String poster, String airDate, int episodeCount, SeriesSeasonData seriesSeasonData) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.poster = poster;
        this.airDate = airDate;
        this.episodeCount = episodeCount;
    }

    public Season(Long id, int seasonNumber, String poster, String airDate, int episodeCount, String backdropPath, String title, String description) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.poster = poster;
        this.airDate = airDate;
        this.episodeCount = episodeCount;
        this.backdropPath = backdropPath;
        this.title = title;
        this.description = description;
    }

    public Season() {
    }

    public Season(Long id, int seasonNumber, String poster, String airDate, int episodeCount) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.poster = poster;
        this.airDate = airDate;
        this.episodeCount = episodeCount;
    }

    protected Season(Parcel in) {
        id = in.readLong();
        seasonNumber = in.readInt();
        poster = in.readString();
        airDate = in.readString();
        episodeCount = in.readInt();
        backdropPath = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Season> CREATOR = new Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel in) {
            return new Season(in);
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getPoster() {
        return IMAGE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return IMAGE_PATH + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(seasonNumber);
        dest.writeString(poster);
        dest.writeString(airDate);
        dest.writeInt(episodeCount);
        dest.writeString(backdropPath);
        dest.writeString(title);
        dest.writeString(description);
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", seasonNumber=" + seasonNumber +
                ", poster='" + poster + '\'' +
                ", airDate='" + airDate + '\'' +
                ", episodeCount=" + episodeCount +
                '}';
    }
}
