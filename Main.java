import network_tools.Dijkstra;
import network_tools.Floyd;
import network_tools.XmlHelper;
import objects.Network;

import java.io.*;
import java.util.*;

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
                    // format de sortie -> à générer avec votre code
                    // imprimer "inf" à la place Integer.MAX_VALUE
                    System.out.println("0 34 74 123 157 101 184 180 222 246 271 387 316 263 101");
                    System.out.println("34 0 40 89 123 67 150 146 188 212 237 353 282 229 67");
                    System.out.println("74 40 0 49 83 42 125 121 163 187 212 328 257 227 107");
                    System.out.println("123 89 49 0 34 60 107 94 136 160 185 301 239 209 156");
                    System.out.println("157 123 83 34 0 66 73 60 102 126 151 267 205 175 190");
                    System.out.println("101 67 42 60 66 0 83 79 121 145 170 286 215 185 134");
                    System.out.println("184 150 125 107 73 83 0 46 88 112 137 253 132 102 217");
                    System.out.println("180 146 121 94 60 79 46 0 42 66 91 207 178 148 213");
                    System.out.println("222 188 163 136 102 121 88 42 0 87 133 249 220 190 255");
                    System.out.println("246 212 187 160 126 145 112 66 87 0 99 215 244 199 279");
                    System.out.println("271 237 212 185 151 170 137 91 133 99 0 116 184 100 262");
                    System.out.println("387 353 328 301 267 286 253 207 249 215 116 0 300 216 378");
                    System.out.println("316 282 257 239 205 215 132 178 220 244 184 300 0 84 246");
                    System.out.println("263 229 227 209 175 185 102 148 190 199 100 216 84 0 162");
                    System.out.println("101 67 107 156 190 134 217 213 255 279 262 378 246 162 0");
                    break;
                case 5:
                    // format de sortie -> à générer avec votre code
                    // imprimer -1 si pas de prédécesseur
                    Floyd.solve(network);
                    System.out.println();
                    System.out.println("-1 0 1 2 3 1 5 5 7 7 7 10 6 14 1");
                    System.out.println("1 -1 1 2 3 1 5 5 7 7 7 10 6 14 1");
                    System.out.println("1 2 -1 2 3 2 5 5 7 7 7 10 6 6 1");
                    System.out.println("1 2 3 -1 3 3 4 4 7 7 7 10 6 6 1");
                    System.out.println("1 2 3 4 -1 4 4 4 7 7 7 10 6 6 1");
                    System.out.println("1 5 5 5 5 -1 5 5 7 7 7 10 6 6 1");
                    System.out.println("1 5 5 4 6 6 -1 6 7 7 7 10 6 6 1");
                    System.out.println("1 5 5 4 7 7 7 -1 7 7 7 10 6 6 1");
                    System.out.println("1 5 5 4 7 7 7 8 -1 8 7 10 6 6 1");
                    System.out.println("1 5 5 4 7 7 7 9 9 -1 9 10 6 10 1");
                    System.out.println("1 5 5 4 7 7 7 10 7 10 -1 10 13 10 13");
                    System.out.println("1 5 5 4 7 7 7 10 7 10 11 -1 13 10 13");
                    System.out.println("1 5 5 4 6 6 12 6 7 7 13 10 -1 12 13");
                    System.out.println("1 14 5 4 6 6 13 6 7 10 13 10 13 -1 13");
                    System.out.println("1 14 1 2 3 1 5 5 7 7 13 10 13 14 -1");
                    break;
                case 6:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Distance: ");
                    System.out.println(network.distance(str1, str2));
                    break;
                case 7:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Parcours: ");
                    System.out.println("[Geneve:Lausanne:Berne:Zurich:Coire]");
                    break;
                case 8:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    Dijkstra.solve(str1, network, 8);
                    break;
                case 9:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    Dijkstra.solve(str1, network, 9);
                    break;
                case 10:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Distance: ");
                    // format de sortie -> à générer avec votre code
                    // imprimer "inf" à la place Integer.MAX_VALUE
                    Dijkstra.solve(str1, str2, network, 10);
                    //System.out.println(267); // résultat pour Bale à St.-Moritz
                    break;
                case 11:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.print("Parcours: ");
                    // format de sortie -> à générer avec votre code
                    Dijkstra.solve(str1, str2, network, 11);
                    //System.out.println("[Bale:Zurich:Coire:St.-Moritz]"); // résultat pour Bale à St.-Moritz
                    break;
                case 12:
                    System.err.println("Nom de la ville:");
                    str1 = in.next();
                    // mise à jour à faire avec votre code
                    break;
                case 13:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    System.err.println("Temps de parcours:");
                    str3 = in.next();
                    // mise à jour à faire avec votre code
                    break;
                case 14:
                    System.err.println("Nom de la ville:");
                    str1 = in.next();
                    // mise à jour à faire avec votre code
                    break;
                case 15:
                    System.err.println("Ville d'origine:");
                    str1 = in.next();
                    System.err.println("Ville de destination:");
                    str2 = in.next();
                    // mise à jour à faire avec votre code
                    break;
                case 16:
                    // format de sortie -> à générer avec votre code
                    Dijkstra.solve(network.getCities().get(0).getName(), network, 16);
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
