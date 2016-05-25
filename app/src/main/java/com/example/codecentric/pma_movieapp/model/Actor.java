package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class Actor implements Parcelable{

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("name")
    private String name;
    @SerializedName("known_for")
    private List<Movie> knownFor; //TODO razmisli da li ces ovako predstavljati filmove ili preko stringa samo nazive
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("profile_path")
    private String profilePath;

    public Actor() {
    }

    public Actor(Parcel p) {

        name = p.readString();
        knownFor = p.readArrayList(Movie.class.getClassLoader());
        popularity = p.readDouble();
        profilePath = p.readString();
    }

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<Movie> knownFor) {
        this.knownFor = knownFor;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return IMAGE_PATH + profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeList(knownFor);
        dest.writeDouble(popularity);
        dest.writeString(profilePath);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", knownFor=" + knownFor +
                ", popularity=" + popularity +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }

    public static class ActorResault{

       private List<Actor> results;

        public List<Actor> getResults() {
            return results;
        }
    }
}

