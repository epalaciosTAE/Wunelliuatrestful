
package com.tae.wunelliuatrestful.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EndDate implements Parcelable {

    private String date;
    private Integer timezoneType;
    private String timezone;

    protected EndDate(Parcel in) {
        date = in.readString();
        timezone = in.readString();
    }

    public static final Creator<EndDate> CREATOR = new Creator<EndDate>() {
        @Override
        public EndDate createFromParcel(Parcel in) {
            return new EndDate(in);
        }

        @Override
        public EndDate[] newArray(int size) {
            return new EndDate[size];
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
