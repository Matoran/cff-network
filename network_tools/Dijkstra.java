package network_tools;

import javafx.util.Pair;
import objects.City;
import objects.Network;

import java.util.ArrayList;

/**
 * Created by matoran on 3/19/17.
 */
public class Dijkstra {


    private static int minIndex(int[] distances, boolean[] visited){
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distances.length; i++) {
            if(!visited[i] && min > distances[i]){
                min = distances[i];
                index = i;
            }
        }
        return index;
    }

    public static int[] solve(String begin, Network network, int display){
        int result[] = new int[network.getCities().size()];
        boolean visited[] = new boolean[network.getCities().size()];
        int precedence[] = new int[network.getCities().size()];

        int index = network.getCityIndexByName(begin);
        for (int i = 0; i < network.getCities().size(); i++) {
            if(network.getDistanceMatrix()[index][i] != Integer.MAX_VALUE)
                precedence[i] = index;
            else
                precedence[i] = -1;
            result[i] = network.getDistanceMatrix()[index][i];
        }
        visited[index] = true;

        while((index = minIndex(result, visited)) != -1){
            for (int i = 0; i < network.getDistanceMatrix().length; i++) {
                if(network.getDistanceMatrix()[index][i] != Integer.MAX_VALUE) {
                    if (result[i] > network.getDistanceMatrix()[index][i] + result[index]) {
                        result[i] = network.getDistanceMatrix()[index][i] + result[index];
                        precedence[i] = index;
                    }
                }
            }
            visited[index] = true;
        }
        //System.out.println("[Geneve<-Lausanne] [Lausanne<-Neuchatel] [Neuchatel<-Delemont] [Delemont<-Bale] [Lausanne<-Berne] [Berne<-Lucerne] [Berne<-Zurich] [Zurich<-Schaffouse] [Zurich<-St.-Gall] [Zurich<-Coire] [Coire<-St.-Moritz] [Lucerne<-Bellinzone] [Sion<-Andermatt] [Lausanne<-Sion]"); // résultat pour Geneve

        switch (display) {
            case 8:
                for (int i = 0; i < result.length; i++) {
                    System.out.print("[" + network.getCities().get(i).getName() + ":" +result[i] + "] ");
                }
                break;
            case 9:
                for (int i = 0; i < result.length; i++) {
                    if(i != precedence[i])
                        System.out.print("[" + network.getCities().get(precedence[i]).getName() + "<-" +network.getCities().get(i).getName() + "] ");
                }
                break;
            case 10:

                break;
            case 11:
                break;
            default:
                System.out.println("unknow display mode");

        }

        System.out.println();
        return result;
    }
}
