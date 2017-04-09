package objects;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date March and April 2017
 * @file City.java
 *
 * City
 *
 */
public class City {
    //--------------------Attributes----------------------------
    private String name;
    private int longitude;
    private int latitude;

    //--------------------Methods-------------------------------
    /**
     * Constructor of city
     * @param name of city
     * @param longitude of city
     * @param latitude of city
     */
    public City(String name, int longitude, int latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Constructor of city
     * @param name of city
     */
    public City(String name) {
        this.name = name;
        longitude = latitude = 0;
    }

    //--------------------Getter-------------------------------
    public String getName() { return name; }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }
}
