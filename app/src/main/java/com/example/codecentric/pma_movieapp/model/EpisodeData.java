package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 17.6.16..
 */
public class EpisodeData implements Parcelable{

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("overview")
    private String seasonOverview;

    @SerializedName("episodes")
    private List<Episode> episodes;

    @SerializedName("poster_path")
    private String poster;


    protected EpisodeData(Parcel in) {
        seasonOverview = in.readString();
        episodes = in.readArrayList(Episode.class.getClassLoader());
        poster = in.readString();
    }

    public static final Creator<EpisodeData> CREATOR = new Creator<EpisodeData>() {
        @Override
        public EpisodeData createFromParcel(Parcel in) {
            return new EpisodeData(in);
        }

        @Override
        public EpisodeData[] newArray(int size) {
            return new EpisodeData[size];
        }
    };

    public String getSeasonOverview() {
        return seasonOverview;
    }

    public void setSeasonOverview(String seasonOverview) {
        this.seasonOverview = seasonOverview;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public String getPoster() {
        return IMAGE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seasonOverview);
        dest.writeList(episodes);
        dest.writeString(poster);
    }


}
