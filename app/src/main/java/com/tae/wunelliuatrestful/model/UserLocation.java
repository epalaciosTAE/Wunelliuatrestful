package com.tae.wunelliuatrestful.model;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class UserLocation {

    private Coordinate coordinate;
    private float speed;
    private float heading;
    private int accuracy;
    private String timestamp;

    public UserLocation(Coordinate coordinate, float speed, float heading, int accuracy, String timestamp) {
        this.coordinate = coordinate;
        this.speed = speed;
        this.heading = heading;
        this.accuracy = accuracy;
        this.timestamp = timestamp;
    }
}
