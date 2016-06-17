package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by codecentric on 15.6.16..
 */
public class SeriesSeasonData implements Parcelable{

    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private Long id;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("seasons")
    private List<Season> seasons;

    public SeriesSeasonData() {
    }



    public SeriesSeasonData(Parcel in) {
        id = in.readLong();
        backdropPath = in.readString();
        title = in.readString();
        description = in.readString();
        seasons = in.readArrayList(Season.class.getClassLoader());
    }


    public static final Creator<SeriesSeasonData> CREATOR = new Creator<SeriesSeasonData>() {
        @Override
        public SeriesSeasonData createFromParcel(Parcel in) {
            return new SeriesSeasonData(in);
        }

        @Override
        public SeriesSeasonData[] newArray(int size) {
            return new SeriesSeasonData[size];
        }
    };

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return IMAGE_PATH + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeLong(id);
        parcel.writeString(backdropPath);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeList(seasons);
    }

    @Override
    public String toString() {
        return "SeriesSeasonData{" +
                "id=" + id +
                ", backdropPath='" + backdropPath + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", seasons=" + seasons +
                '}';
    }
}
