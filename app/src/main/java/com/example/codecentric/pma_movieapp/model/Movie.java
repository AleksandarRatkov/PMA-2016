package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class Movie implements Parcelable{
    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    //TODO da li je potreban mozda id zbog kasnijeg rate-inga

    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("vote_average")
    private double voteAverage;

    public Movie() {
    }

    public Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        voteAverage = in.readDouble();
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
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeDouble(voteAverage);
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }
}
