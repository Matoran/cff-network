package network_tools;

import objects.Network;

import static java.lang.Math.min;

/**
 * Created by matoran on 3/25/17.
 */
public class Floyd {

    public static void solve(Network network){
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
                if(precedence[i][j] == 0){
                    precedence[i][j] = -1;
                }else if(precedence[i][j] != Integer.MAX_VALUE){
                    precedence[i][j] = i;
                }
            }
        }
        for (int k = 0; k < distance.length; k++) {
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    if(distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE)
                        continue;
                    if(distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                        precedence[i][j] = precedence[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                if(distance[i][j] == Integer.MAX_VALUE){
                    System.out.print("-\t");
                }else{
                    System.out.print(distance[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("=======================================");
        for (int i = 0; i < precedence.length; i++) {
            for (int j = 0; j < precedence[i].length; j++) {
                if(precedence[i][j] == Integer.MAX_VALUE){
                    System.out.print("-\t");
                }else{
                    System.out.print(precedence[i][j] + "\t");
                }
            }
            System.out.println();
        }


        System.out.println("=======================================");
    }
}
