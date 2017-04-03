package objects;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by matoran on 3/15/17.
 */
public class Network {
    private String name;
    private ArrayList<City> cities;
    private ArrayList<Connection> connections;
    private int distanceMatrix[][];

    public Network(String name) {
        this.name = name;
        cities = new ArrayList<>();
        connections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public City getCityByName(String city) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(city)) {
                return cities.get(i);
            }
        }
        System.out.println("unknow city: " + city);
        System.exit(1);
        return null;
    }

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

    public void addConnection(String city, String city2, Integer distance) {
        connections.add(new Connection(getCityIndexByName(city), getCityIndexByName(city2), distance));
        /*City c = getCityByName(city);
        City c2 = getCityByName(city2);
        c.addConnection(c2, distance);
        c2.addConnection(c, distance);*/
        update();
    }

    public void displayCities() {
        //format [0:Geneve] [1:Lausanne]
        for (int i = 0; i < cities.size(); i++) {
            System.out.print("[" + i + ":" + cities.get(i).getName() + "] ");
        }
        System.out.println();
    }

    public void displayDistanceMatrix() {
        //System.out.println("0 34 inf inf inf inf inf inf inf inf inf inf inf inf inf");
        //System.out.println("34 0 40 inf inf 67 inf inf inf inf inf inf inf inf 67");
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

    public void displayDistanceList() {
        //System.out.println("Geneve [Lausanne:34]");
        //System.out.println("Lausanne [Geneve:34] [Neuchatel:40] [Berne:67] [Sion:67]");
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

    public int distance(String str1, String str2) {
        return 0;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void addCity(String name) {
        cities.add(new City(name));
        update();
    }

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
}
