
package com.tae.wunelliuatrestful.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StartCoordinate implements Parcelable{

    private Double lat;
    private Double lng;

    protected StartCoordinate(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public static final Creator<StartCoordinate> CREATOR = new Creator<StartCoordinate>() {
        @Override
        public StartCoordinate createFromParcel(Parcel in) {
            return new StartCoordinate(in);
        }

        @Override
        public StartCoordinate[] newArray(int size) {
            return new StartCoordinate[size];
        }
    };

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }
}
