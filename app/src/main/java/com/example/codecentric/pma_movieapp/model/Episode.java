package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 14.6.16..
 */
public class Episode implements Parcelable {

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("overview")
    private String description;

    @SerializedName("still_path")
    private String poster;

    @SerializedName("episode_number")
    private int episodeNumber;

    @SerializedName("air_date")
    private String airDate;

    private String seasonPoster;

    public Episode() {
    }

    public Episode(Long id,String poster, String name, String description, int episodeNumber, String airDate) {
        this.id = id;
        this.poster = poster;
        this.name = name;
        this.description = description;
        this.episodeNumber = episodeNumber;
        this.airDate = airDate;

    }

    public Episode(Parcel p) {
        id = p.readLong();
        name = p.readString();
        description = p.readString();
        poster = p.readString();
        episodeNumber = p.readInt();
        airDate = p.readString();
        seasonPoster = p.readString();
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return IMAGE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getVoteAverage() {
        return episodeNumber;
    }

    public void setVoteAverage(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getSeasonPoster() {
        return IMAGE_PATH + seasonPoster;
    }

    public void setSeasonPoster(String seasonPoster) {
        this.seasonPoster = seasonPoster;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", episodeNumber=" + episodeNumber +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(poster);
        parcel.writeInt(episodeNumber);
        parcel.writeString(airDate);
        parcel.writeString(seasonPoster);

    }
}
