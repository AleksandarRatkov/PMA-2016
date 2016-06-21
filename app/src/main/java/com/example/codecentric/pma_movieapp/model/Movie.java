package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class Movie extends RealmObject implements Parcelable{
    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private Long id;

    private String title;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("original_name")
    private String titleS;


    @SerializedName("first_air_date")
    private String firstAirDate;

    public Movie() {
    }

    public Movie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        voteAverage = in.readDouble();
        releaseDate = in.readString();
        titleS = in.readString();
        firstAirDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    public String getTitleS() {
        return titleS;
    }

    public void setTitleS(String titleS) {
        this.titleS = titleS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripton) {
        this.description = descripton;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        voteAverage = voteAverage;
    }

    public String getPoster() {
        return IMAGE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return IMAGE_PATH  + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", description='" + description + '\'' +
                ", backdrop='" + backdrop + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeDouble(voteAverage);
        parcel.writeString(releaseDate);
        parcel.writeString(titleS);
        parcel.writeString(firstAirDate);
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }
}
