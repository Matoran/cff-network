package objects;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by matoran on 3/15/17.
 */
public class City {
    private String name;
    private int longitude;
    private int latitude;

    public City(String name, int longitude, int latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public City(String name) {
        this.name = name;
        longitude = latitude = 0;
    }
    public String getName() {
        return name;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }
}
