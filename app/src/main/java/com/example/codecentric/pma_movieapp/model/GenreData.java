package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 21.6.16..
 */
public class GenreData implements Parcelable{

    private List<Genre> genres;

    public GenreData() {
    }

    public GenreData(List<Genre> genres) {
        this.genres = genres;
    }

    protected GenreData(Parcel in) {
        genres = in.createTypedArrayList(Genre.CREATOR);
    }

    public static final Creator<GenreData> CREATOR = new Creator<GenreData>() {
        @Override
        public GenreData createFromParcel(Parcel in) {
            return new GenreData(in);
        }

        @Override
        public GenreData[] newArray(int size) {
            return new GenreData[size];
        }
    };

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(genres);
    }
}
