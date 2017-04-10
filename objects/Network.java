package objects;

import java.util.ArrayList;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date March and April 2017
 * @file Network.java
 *
 * Manages the network of cities
 *
 */
public class Network {
    //--------------------Attributes----------------------------
    private String name;
    private ArrayList<City> cities;
    private ArrayList<Connection> connections;
    private int distanceMatrix[][];

    //--------------------Methods-------------------------------

    /**
     * Constructor
     * @param name of network
     */
    public Network(String name) {
        this.name = name;
        cities = new ArrayList<>();
        connections = new ArrayList<>();
    }

    //--------------------Add--------------------------------
    /*
    * Add city or connection in network
    * */
    public void addCity(City city) {
        cities.add(city);
    }

    public void addCity(String name) {
        cities.add(new City(name));
        update();
    }

    public void addConnection(String city, String city2, Integer distance) {
        connections.add(new Connection(getCityIndexByName(city), getCityIndexByName(city2), distance));
        update();
    }

    //--------------------Display-------------------------------
    /*
    * Displays cities, distance matrix or distance list
     */
    public void displayCities() {
        for (int i = 0; i < cities.size(); i++) {
            System.out.print("[" + i + ":" + cities.get(i).getName() + "] ");
        }
        System.out.println();
    }

    public void displayDistanceMatrix() {
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                if (distanceMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("inf ");
                } else {
                    System.out.print(distanceMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayDistanceList() {
        for (City city : cities) {
            System.out.print(city.getName() + " ");
            int index = getCityIndexByName(city.getName());
            for (int i = 0; i < distanceMatrix.length; i++) {
                int temp = distanceMatrix[index][i];
                if(temp != Integer.MAX_VALUE && temp != 0){
                    System.out.print("[" + cities.get(i).getName() + ":" + temp + "] ");
                }
            }
            System.out.println();
        }
    }

    //--------------------Update-------------------------------

    /**
     * Update the distance matrix
     */
    public void update() {
        distanceMatrix = new int[cities.size()][cities.size()];
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    distanceMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (Connection connection : connections) {
            distanceMatrix[connection.getCity1()][connection.getCity2()] = connection.getDistance();
            distanceMatrix[connection.getCity2()][connection.getCity1()] = connection.getDistance();
        }
    }

    //--------------------Remove-------------------------------

    /**
     * Remove connection between two cities
     * @param city1 name of city 1
     * @param city2 name of city 2
     */
    public void removeConnection(String city1, String city2) {
        int c1 = getCityIndexByName(city1);
        int c2 = getCityIndexByName(city2);
        for (int i = connections.size() - 1; i >= 0; i--) {
            if((connections.get(i).getCity1() == c1 && connections.get(i).getCity2() == c2)
                    || (connections.get(i).getCity1() == c2 && connections.get(i).getCity2() == c1)){
                connections.remove(i);
                break;
            }
        }
        update();
    }

    /**
     * Remove a city and it's connections
     * @param name of city
     */
    public void removeCity(String name) {
        int index = getCityIndexByName(name);
        for (int i = connections.size() - 1; i >= 0; i--) {
            if(connections.get(i).getCity1() == index || connections.get(i).getCity2() == index){
                connections.remove(i);
            }else{
                if(connections.get(i).getCity1() > index){
                    connections.get(i).updateCity1();
                }
                if(connections.get(i).getCity2() > index){
                    connections.get(i).updateCity2();
                }
            }

        }

        cities.remove(index);
        update();
    }

    //--------------------Getter-------------------------------
    public int getCityIndexByName(String city) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(city)) {
                return i;
            }
        }
        System.out.println("unknow city: " + city);
        System.exit(1);
        return -1;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Connection> getConnections() {
        return connections;
    }
    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }
    public ArrayList<City> getCities() {
        return cities;
    }
}
