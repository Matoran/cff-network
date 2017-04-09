package network_tools;

import objects.City;
import objects.Network;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by matoran on 3/25/17.
 */
public class Floyd {
    private static int distance[][];
    private static int precedence[][];
    private static int city1Index;
    private static int city2Index;
    private static ArrayList<City> path = new ArrayList<>();

    public static int distance(Network network, String city1, String city2){
        solve(network, city1, city2, 6);
        return distance[city1Index][city2Index];
    }

    public static ArrayList<City> path(Network network, String city1, String city2){
        path.clear();
        solve(network, city1, city2, 7);
        return path;
    }

    private static void displayMatrix(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("-\t");
                } else {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void solve(Network network, int display) {
        solve(network, null, null, display);
    }

    public static void solve(Network network, String city1, String city2, int display) {
        distance = network.getDistanceMatrix().clone();
        for (int i = 0; i < distance.length; i++) {
            distance[i] = distance[i].clone();
        }
        precedence = network.getDistanceMatrix().clone();
        for (int i = 0; i < precedence.length; i++) {
            precedence[i] = precedence[i].clone();
        }
        for (int i = 0; i < precedence.length; i++) {
            for (int j = 0; j < precedence[i].length; j++) {
                if (precedence[i][j] == 0) {
                    precedence[i][j] = -1;
                } else if (precedence[i][j] != Integer.MAX_VALUE) {
                    precedence[i][j] = i;
                }
            }
        }
        for (int k = 0; k < distance.length; k++) {
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    if (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE)
                        continue;
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        precedence[i][j] = precedence[k][j];
                    }
                }
            }
        }

        city1Index = 0;
        city2Index = 0;
        if (display == 6 || display == 7) {
            city1Index = network.getCityIndexByName(city1);
            city2Index = network.getCityIndexByName(city2);
        }

        switch (display) {
            case 4:
                displayMatrix(distance);
                break;
            case 5:
                displayMatrix(precedence);
                break;
            case 6:
                System.out.println(distance[city1Index][city2Index]);
                break;
            case 7:
                int current = city2Index;

                Stack<String> stack = new Stack<>();
                while (current != -1 && current != Integer.MAX_VALUE) {
                    stack.add(network.getCities().get(current).getName());
                    path.add(network.getCities().get(current));
                    current = precedence[city1Index][current];
                }
                if (current != Integer.MAX_VALUE) {
                    System.out.print("[");
                    while (!stack.isEmpty()) {
                        System.out.print(stack.pop() + (stack.size() > 0 ? ":" : ""));
                    }
                    System.out.println("]");
                }


                //System.out.println("[Geneve:Lausanne:Berne:Zurich:Coire]");
                break;
            default:
                System.out.println("unknow display for Floyd");
        }
    }
}
