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
    private ArrayList<Pair<City,Integer>> connections;

    public City(String name, int longitude, int latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        connections = new ArrayList<>();
    }

    public void addConnection(City city, Integer distance){
        connections.add(new Pair<>(city, distance));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pair<City, Integer>> getConnections() {
        return connections;
    }
}
