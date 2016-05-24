package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class Series implements Parcelable {

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("original_name")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("first_air_date")
    private String firstAirDate;


    public Series() {
    }

    public Series(Parcel p) {
        title = p.readString();
        poster = p.readString();
        description = p.readString();
        backdrop = p.readString();
        voteAverage = p.readDouble();
        firstAirDate = p.readString();
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPoster() {
        return IMAGE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return IMAGE_PATH + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @Override
    public String toString() {
        return "Series{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", backdrop='" + backdrop + '\'' +
                ", voteAverage=" + voteAverage +
                ", firstAirDate='" + firstAirDate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeDouble(voteAverage);
        parcel.writeString(firstAirDate);
    }

    public static class SeriesResult {
        private List<Series> results;

        public List<Series> getResults() {
            return results;
        }
    }
}