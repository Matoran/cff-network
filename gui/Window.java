package gui;

import network_tools.Dijkstra;
import network_tools.Floyd;
import network_tools.XmlHelper;
import objects.City;
import objects.Network;

import javax.swing.*;
import java.io.File;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date March and April 2017
 * @file Window.java
 *
 * Create the window and the menu
 *
 */
public class Window {
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "villes.xml";
        Network network = XmlHelper.loadNetwork(filePath);
        network.update();
        JFrame frame = new JFrame(network.getName());

        //the menu creation
        JMenuBar menuBar = new JMenuBar();

        JMenu cities = new JMenu("Cities");
        JMenuItem newCity = new JMenuItem("New");
        newCity.addActionListener(e -> {
            JTextField name = new JTextField();
            JSpinner longitude = new JSpinner();
            JSpinner latitude = new JSpinner();
            Object[] message = {
                    "Name: ", name,
                    "Longitude: ", longitude,
                    "Latitude: ", latitude
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Add city", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                network.addCity(new City(name.getText(), (int)longitude.getValue(), (int)latitude.getValue()));
                frame.getContentPane().repaint();
            }
        });
        JMenuItem rmCity = new JMenuItem("Remove");
        rmCity.addActionListener(e -> {
            JTextField name = new JTextField();
            Object[] message = {
                    "Name: ", name,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Remove city", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                network.removeCity(name.getText());
                frame.getContentPane().repaint();
            }
        });
        cities.add(newCity);
        cities.add(rmCity);

        JMenu connections = new JMenu("Connections");
        JMenuItem newConnection = new JMenuItem("New");
        newConnection.addActionListener(e -> {
            JTextField city1 = new JTextField();
            JTextField city2 = new JTextField();
            JSpinner distance = new JSpinner();
            Object[] message = {
                    "Name city 1: ", city1,
                    "Name city 2: ", city2,
                    "Distance: ", distance
            };

            int option = JOptionPane.showConfirmDialog(null, message, "New connection", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                network.addConnection(city1.getText(), city2.getText(), (int)distance.getValue());
                frame.getContentPane().repaint();
            }
        });
        JMenuItem rmConnection = new JMenuItem("Remove");
        rmConnection.addActionListener(e -> {
            JTextField city1 = new JTextField();
            JTextField city2 = new JTextField();
            Object[] message = {
                    "Name city 1: ", city1,
                    "Name city 2: ", city2,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Remove connection", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                network.removeConnection(city1.getText(), city2.getText());
                frame.getContentPane().repaint();
            }
        });
        connections.add(newConnection);
        connections.add(rmConnection);

        JMenu dijkstra = new JMenu("Dijkstra");
        JMenuItem dijkstraDistance = new JMenuItem("Distance");
        dijkstraDistance.addActionListener(e -> {
            JTextField city1 = new JTextField();
            JTextField city2 = new JTextField();
            Object[] message = {
                    "Name city 1: ", city1,
                    "Name city 2: ", city2,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Dijkstra distance", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, Dijkstra.distance(network, city1.getText(),city2.getText()));
            }
        });
        JMenuItem dijkstraPath = new JMenuItem("Path");
        dijkstraPath.addActionListener(e -> {
            JTextField city1 = new JTextField();
            JTextField city2 = new JTextField();
            Object[] message = {
                    "Name city 1: ", city1,
                    "Name city 2: ", city2,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Dijkstra path", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                ((Panel)frame.getContentPane()).setPath(Dijkstra.path(network, city1.getText(), city2.getText()));
                frame.getContentPane().repaint();
            }
        });
        dijkstra.add(dijkstraDistance);
        dijkstra.add(dijkstraPath);


        JMenu floyd = new JMenu("Floyd");
        JMenuItem floydDistance = new JMenuItem("Distance");
        floydDistance.addActionListener(e -> {
            JTextField city1 = new JTextField();
            JTextField city2 = new JTextField();
            Object[] message = {
                    "Name city 1: ", city1,
                    "Name city 2: ", city2,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Floyd distance", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, Floyd.distance(network, city1.getText(),city2.getText()));
            }
        });
        JMenuItem floydPath = new JMenuItem("Path");
        floydPath.addActionListener(e -> {
            JTextField city1 = new JTextField();
            JTextField city2 = new JTextField();
            Object[] message = {
                    "Name city 1: ", city1,
                    "Name city 2: ", city2,
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Floyd path", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                ((Panel)frame.getContentPane()).setPath(Floyd.path(network, city1.getText(), city2.getText()));
                frame.getContentPane().repaint();
            }
        });
        floyd.add(floydDistance);
        floyd.add(floydPath);

        JMenuItem connectivity = new JMenuItem("Connectivity");
        connectivity.addActionListener(e -> JOptionPane.showMessageDialog(null, Dijkstra.connectivity(network)));


        menuBar.add(cities);
        menuBar.add(connections);
        menuBar.add(dijkstra);
        menuBar.add(floyd);
        menuBar.add(connectivity);
        frame.setJMenuBar(menuBar);
        //end of menu creation

        frame.setContentPane(new Panel(network));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1500,1000);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
