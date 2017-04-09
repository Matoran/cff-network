import network_tools.Dijkstra;
import network_tools.Floyd;
import network_tools.XmlHelper;
import objects.Network;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @brief main
 * @version 0.1
 * @date March and April 2017
 * @file Main.java
 *
 * Main
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // permet de prendre les entrées pour le menu
        // soit du clavier, d'un fichier ou de la ligne de commande
        Scanner in;
        switch (args.length) {
            case 0:
                in = new Scanner(System.in);
                break;
            case 1:
                in = new Scanner(new File(args[0]));
                break;
            default:
                String source = args[0];
                for (int i = 1; i < args.length; i++) source += " " + args[i];
                in = new Scanner(source);
        }
        String filePath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "villes.xml";
        //lire le fichier villes.xml avec votre code
        Network network = XmlHelper.loadNetwork(filePath);
        network.update();
        System.err.println("Le fichier XML " + filePath + " a été chargé\n");
        int choix = 0;
        do {
            // les impressions du menu sont envoyées sur le canal d'erreur
            // pour les différencier des sorties de l'application
            // lesquelles sont envoyées sur la sortie standard
            System.err.println("Choix  0: quitter");
            System.err.println("Choix  1: liste des villes");
            System.err.println("Choix  2: matrice des poids");
            System.err.println("Choix  3: liste des poids");
            System.err.println("Choix  4: matrice des temps de parcours (Floyd)");
            System.err.println("Choix  5: matrice des précédences (Floyd)");
            System.err.println("Choix  6: temps de parcours entre deux villes (Floyd)");
            System.err.println("Choix  7: parcours entre deux villes (Floyd)");
            System.err.println("Choix  8: tableau des temps de parcours (Dijkstra)");
            System.err.println("Choix  9: tableau des précédences (Dijkstra)");
            System.err.println("Choix 10: temps de parcours entre deux villes (Dijkstra)");
            System.err.println("Choix 11: parcours entre deux villes (Dijkstra)");
            System.err.println("Choix 12: ajout d'une ville");
            System.err.println("Choix 13: ajout d'une liaison");
            System.err.println("Choix 14: suppression d'une ville");
            System.err.println("Choix 15: suppression d'une liaison");
            System.err.println("Choix 16: graphe connexe?");
            System.err.println("Choix 17: sauver (format XML)");

            System.err.println("Entrez votre choix: ");
            choix = in.nextInt();
            String str1, str2, str3;
            switch (choix) {
                case 1:
                    // format de sortie -> à générer avec votre code
                    network.displayCities();
                    break;
                case 2:
                    // format de sortie -> à générer avec votre code
                    // imprimer "inf" à la place Integer.MAX_VALUE
                    network.displayDistanceMatrix();
                    break;
                case 3:
                    // format de sortie -> à générer avec votre code
                    network.displayDistanceList();
                    break;
                case 4:
                    Floyd.solve(network, 4);
                    break;
                case 5:
                    Floyd.solve(network, 5);
                    break;
                case 6:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Distance: ");
                    Floyd.solve(network, str1, str2, 6);
                    break;
                case 7:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Parcours: ");
                    Floyd.solve(network, str1, str2, 7);

                    break;
                case 8:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    Dijkstra.solve(network, str1, 8);
                    break;
                case 9:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    Dijkstra.solve(network, str1, 9);
                    break;
                case 10:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Distance: ");
                    // format de sortie -> à générer avec votre code
                    // imprimer "inf" à la place Integer.MAX_VALUE
                    Dijkstra.solve(network, str1, str2, 10);
                    //System.out.println(267); // résultat pour Bale à St.-Moritz
                    break;
                case 11:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Parcours: ");
                    // format de sortie -> à générer avec votre code
                    Dijkstra.solve(network, str1, str2, 11);
                    //System.out.println("[Bale:Zurich:Coire:St.-Moritz]"); // résultat pour Bale à St.-Moritz
                    break;
                case 12:
                    System.err.println("Nom de la ville:");
                    str1 = in.next();
                    network.addCity(str1);
                    // mise à jour à faire avec votre code
                    break;
                case 13:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.println("Temps de parcours:");
                    str3 = in.next();
                    network.addConnection(str1, str2, Integer.valueOf(str3));
                    // mise à jour à faire avec votre code
                    break;
                case 14:
                    System.err.println("Nom de la ville:");
                    str1 = in.next();
                    // mise à jour à faire avec votre code
                    network.removeCity(str1);
                    break;
                case 15:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    network.removeConnection(str1, str2);
                    // mise à jour à faire avec votre code
                    break;
                case 16:
                    // format de sortie -> à générer avec votre code
                    Dijkstra.solve(network, network.getCities().get(0).getName(), 16);
                    break;
                case 17:
                    System.err.println("Nom du fichier XML:");
                    str1 = in.next();
                    // sauvegarde à faire avec votre code
                    XmlHelper.saveNetwork(network, str1);
                    break;
            }
        } while (choix != 0);
    }
}
