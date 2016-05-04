
package com.tae.wunelliuatrestful.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EndCoordinate implements Parcelable{

    private Double lat;
    private Double lng;

    protected EndCoordinate(Parcel in) {
    }

    public static final Creator<EndCoordinate> CREATOR = new Creator<EndCoordinate>() {
        @Override
        public EndCoordinate createFromParcel(Parcel in) {
            return new EndCoordinate(in);
        }

        @Override
        public EndCoordinate[] newArray(int size) {
            return new EndCoordinate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
