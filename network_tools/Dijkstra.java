package network_tools;

import objects.City;
import objects.Network;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date March and April 2017
 * @file Dijkstra.java
 *
 * Solve a graph problem with Dijkstra
 *
 */
public class Dijkstra {
    //--------------------Attributes----------------------------
    private static int distance;
    private static ArrayList<City> path = new ArrayList<>();
    private static boolean connectivity;

    //--------------------Methods-------------------------------
    /**
     * Return the travel time between two cities for the gui
     * @param network network of cities
     * @param city1 name of city1
     * @param city2 name of city2
     * @return the number between city1 and city2
     */
    public static int distance(Network network, String city1, String city2){
        solve(network, city1, city2, 10);
        return distance;
    }

    /**
     * Calculates the travel between two cities for the gui
     * @param network network of cities
     * @param city1 name of city1
     * @param city2 name of city2
     * @return the travel between city1 and city2
     */
    public static ArrayList<City> path(Network network, String city1, String city2){
        path.clear();
        solve(network, city1, city2, 11);
        return path;
    }

    /**
     * Calculates the nearest city that has never been traveled
     * @param distances array of distance for a city
     * @param visited array of city already traveled
     * @return the next city
     */
    private static int minIndex(int[] distances, boolean[] visited) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && min > distances[i]) {
                min = distances[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * Solve a graph problem with dijkstra
     * @param network network of cities
     * @param begin the city of departure
     * @param display option for the display
     */
    public static void solve(Network network, String begin, int display) {
        solve(network, begin, null, display);
    }

    /**
     * Solve a graph problem with dijkstra
     * @param network network of cities
     * @param begin the city of departure
     * @param end the city of arrival
     * @param display option for the display
     */
    public static void solve(Network network, String begin, String end, int display) {
        //Initialization
        int result[] = new int[network.getCities().size()];
        boolean visited[] = new boolean[network.getCities().size()];
        int precedence[] = new int[network.getCities().size()];

        //Step one
        int index = network.getCityIndexByName(begin);
        for (int i = 0; i < network.getCities().size(); i++) {
            if (network.getDistanceMatrix()[index][i] != Integer.MAX_VALUE)
                precedence[i] = index;
            else
                precedence[i] = -1;
            result[i] = network.getDistanceMatrix()[index][i];
        }
        visited[index] = true;

        //Dijkstra
        while ((index = minIndex(result, visited)) != -1) {
            if (network.getCities().get(index).getName().equals(end)) {
                break;
            }
            for (int i = 0; i < network.getDistanceMatrix().length; i++) {
                if (network.getDistanceMatrix()[index][i] != Integer.MAX_VALUE) {
                    if (result[i] > network.getDistanceMatrix()[index][i] + result[index]) {
                        result[i] = network.getDistanceMatrix()[index][i] + result[index];
                        precedence[i] = index;
                    }
                }
            }
            visited[index] = true;
        }

        //Modes of display
        switch (display) {
            case 8:
                for (int i = 0; i < result.length; i++) {
                    System.out.print("[" + network.getCities().get(i).getName() + ":" + result[i] + "] ");
                }
                break;
            case 9:
                for (int i = 0; i < result.length; i++) {
                    if (i != precedence[i])
                        System.out.print("[" + network.getCities().get(precedence[i]).getName() + "<-" + network.getCities().get(i).getName() + "] ");
                }
                break;
            case 10:
                System.out.println(result[network.getCityIndexByName(end)]);
                distance = result[network.getCityIndexByName(end)];
                break;
            case 11:
                int city = network.getCityIndexByName(end);

                int old;
                Stack<String> stack = new Stack<>();
                do {
                    path.add(network.getCities().get(city));
                    stack.add(network.getCities().get(city).getName());
                    old = city;
                } while ((city = precedence[city]) != old && city != -1);
                if (city != -1) {
                    System.out.print("[");
                    while (!stack.isEmpty()) {
                        System.out.print(stack.pop() + (stack.size() > 0 ? ":" : ""));
                    }
                    System.out.println("]");
                }
                break;
            case 16:
                for (int i = 0; i < result.length; i++) {
                    if (result[i] == Integer.MAX_VALUE) {
                        System.out.println(false);
                        connectivity = false;
                        return;
                    }
                }
                connectivity = true;
                System.out.println(true);
                break;
            default:
                System.out.println("unknow display mode");

        }
    }

    /**
     * Test if the graph is connectivity
     * @param network network of cities
     * @return true or false for the connectivity
     */
    public static boolean connectivity(Network network) {
        Dijkstra.solve(network, network.getCities().get(0).getName(), 16);
        return connectivity;
    }
}
