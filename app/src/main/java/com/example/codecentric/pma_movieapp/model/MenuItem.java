package com.example.codecentric.pma_movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class MenuItem implements Parcelable{

    private String mTitle;
    private String mDescription;
    private int mIcon;

    public MenuItem(String mTitle, String mDescription, int mIcon) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mIcon = mIcon;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeInt(mIcon);
    }
}
