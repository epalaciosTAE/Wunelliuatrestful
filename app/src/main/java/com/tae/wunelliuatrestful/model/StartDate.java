
package com.tae.wunelliuatrestful.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StartDate implements Parcelable{

    private String date;
    private Integer timezoneType;
    private String timezone;

    protected StartDate(Parcel in) {
        date = in.readString();
        timezone = in.readString();
    }

    public static final Creator<StartDate> CREATOR = new Creator<StartDate>() {
        @Override
        public StartDate createFromParcel(Parcel in) {
            return new StartDate(in);
        }

        @Override
        public StartDate[] newArray(int size) {
            return new StartDate[size];
        }
    };

    public String getDate() {
        return date;
    }

    public Integer getTimezoneType() {
        return timezoneType;
    }

    public String getTimezone() {
        return timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(timezone);
    }
}
