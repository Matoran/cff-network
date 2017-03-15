package objects;

import java.util.ArrayList;

/**
 * Created by matoran on 3/15/17.
 */
public class City {
    private String name;
    private int longitude;
    private int latitude;
    private ArrayList<City> connections;

    public City(String name, int longitude, int latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        connections = new ArrayList<>();
    }

    public void addConnection(City city){
        connections.add(city);
    }


}
