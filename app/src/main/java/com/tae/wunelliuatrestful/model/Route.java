
package com.tae.wunelliuatrestful.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable{

    private Integer journeyID;
    private String startLocality;
    private StartCoordinate startCoordinate;
    private StartDate startDate;
    private String endLocality;
    private EndCoordinate endCoordinate;
    private EndDate endDate;
    private Integer distance;
    private Integer duration;
    private String route;
    private String invalidReason;

    protected Route(Parcel in) {
        startLocality = in.readString();
        startCoordinate = in.readParcelable(StartCoordinate.class.getClassLoader());
        startDate = in.readParcelable(StartDate.class.getClassLoader());
        endLocality = in.readString();
        endCoordinate = in.readParcelable(EndCoordinate.class.getClassLoader());
        endDate = in.readParcelable(EndDate.class.getClassLoader());
        route = in.readString();
        invalidReason = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startLocality);
        dest.writeParcelable(startCoordinate, flags);
        dest.writeParcelable(startDate, flags);
        dest.writeString(endLocality);
        dest.writeParcelable(endCoordinate, flags);
        dest.writeParcelable(endDate, flags);
        dest.writeString(route);
        dest.writeString(invalidReason);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    public Integer getJourneyID() {
        return journeyID;
    }

    public String getStartLocality() {
        return startLocality;
    }

    public StartCoordinate getStartCoordinate() {
        return startCoordinate;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public String getEndLocality() {
        return endLocality;
    }

    public EndCoordinate getEndCoordinate() {
        return endCoordinate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getRoute() {
        return route;
    }

    public String getInvalidReason() {
        return invalidReason;
    }


}
