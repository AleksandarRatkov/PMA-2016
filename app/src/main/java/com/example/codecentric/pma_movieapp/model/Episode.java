package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by codecentric on 14.6.16..
 */
public class Episode implements Parcelable {

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("name")
    private String name;

    @SerializedName("overview")
    private String description;

    @SerializedName("still_path")
    private String poster;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("air_date")
    private String airDate;

    public Episode() {
    }

    public Episode(String poster, String name, String description, double voteAverage, String airDate) {
        this.poster = poster;
        this.name = name;
        this.description = description;
        this.voteAverage = voteAverage;
        this.airDate = airDate;

    }

    public Episode(Parcel p) {
        name = p.readString();
        description = p.readString();
        poster = p.readString();
        voteAverage = p.readDouble();
        airDate = p.readString();
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

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(poster);
        parcel.writeDouble(voteAverage);
        parcel.writeString(airDate);

    }

    public static class EpisodeResult {
        private List<Episode> results;

        public List<Episode> getResults() {
            return results;
        }
    }
}
