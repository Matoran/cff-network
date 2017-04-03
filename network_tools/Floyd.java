package network_tools;

import objects.Network;

import java.util.Stack;

import static java.lang.Math.min;

/**
 * Created by matoran on 3/25/17.
 */
public class Floyd {

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
        int distance[][] = network.getDistanceMatrix().clone();
        for (int i = 0; i < distance.length; i++) {
            distance[i] = distance[i].clone();
        }
        int precedence[][] = network.getDistanceMatrix().clone();
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

        int city1Index = 0;
        int city2Index = 0;
        if (display == 6 || display == 7) {
            city1Index = network.getCityIndexByName(city1);
            city2Index = network.getCityIndexByName(city2);
        }

        switch (display) {
            case 4:
                displayMatrix(distance);
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
