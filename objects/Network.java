package objects;

import java.util.ArrayList;

/**
 * Created by matoran on 3/15/17.
 */
public class Network {
    private String name;
    private ArrayList<City> cities;
    public Network(String name) {
        this.name = name;
        cities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCity(City city){
        cities.add(city);
    }
}
