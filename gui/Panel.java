package gui;

import objects.City;
import objects.Connection;
import objects.Network;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date March and April 2017
 * @file Panel.java
 *
 * Load data and draw the cff network
 *
 */
public class Panel extends JPanel{
    //--------------------Attributes----------------------------
    private ArrayList<Point> points = new ArrayList<>();
    private Polygon polygon = new Polygon();
    private int minx = Integer.MAX_VALUE, miny =  Integer.MAX_VALUE, maxx = -1, maxy = -1;
    private Network network;
    private ArrayList<City> path;

    //--------------------Methods-------------------------------

    /**
     * load all points to create the switzerland polygon from a file
     * @param network
     */
    public Panel(Network network) {
        this.network = network;
        String filePath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "suisse.txt";
        File file = new File(filePath);

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println("Reading file using Buffered Reader");
            String readLine = "";
            while ((readLine = bufferedReader.readLine()) != null) {
                String xy[] = readLine.split(" ");
                int x = Integer.valueOf(xy[0]);
                int y = Integer.valueOf(xy[1]);
                //save the min and max x,y
                if(x > maxx){
                    maxx = x;
                }else if(x < minx){
                    minx = x;
                }
                if(y > maxy){
                    maxy = y;
                }else if(y < miny){
                    miny = y;
                }
                points.add(new Point(x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * is called on repaint(resize window), draw the panel content
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //calc the switzerland ratio to keep aspect ratio in window
        float ratio = (float)(maxy-miny)/(maxx-minx);
        int width;
        int height;
        if((float)getHeight()/getWidth() < ratio){
            width = (int)(getHeight()/ratio-10);
            height = getHeight()-10;
        }else{
            width = getWidth()-10;
            height = (int)(ratio*getWidth()-10);
        }

        //draw the switzerland
        for (Point point : points) {
            int x = map(point.x, minx, maxx, 10, width);
            int y = map(point.y, miny, maxy, 10, height);
            int mid = getHeight()/2;
            y = mid-(y-mid);
            polygon.addPoint(x, y);
        }
        g.drawPolygon(polygon);

        //draw cities
        for (City city : network.getCities()) {
            int x = map(city.getLongitude(), minx, maxx, 10, width);
            int y = map(city.getLatitude(), miny, maxy, 10, height);
            int mid = getHeight()/2;
            y = mid-(y-mid);
            g.drawRect(x-5, y-5, 10, 10);
            g.drawString(city.getName(), x+10, y+5);
        }

        //draw connections
        for (Connection connection : network.getConnections()) {
            City city1 = network.getCities().get(connection.getCity1());
            City city2 = network.getCities().get(connection.getCity2());
            int city1x = map(city1.getLongitude(), minx, maxx, 10, width);
            int city1y = map(city1.getLatitude(), miny, maxy, 10, height);
            int city2x = map(city2.getLongitude(), minx, maxx, 10, width);
            int city2y = map(city2.getLatitude(), miny, maxy, 10, height);
            int mid = getHeight()/2;
            city1y = mid-(city1y-mid);
            city2y = mid-(city2y-mid);
            g.drawLine(city1x, city1y, city2x, city2y);
            g.drawString(String.valueOf(connection.getDistance()), (city1x+city2x)/2, (city1y+city2y)/2);
        }

        //draw a path(Dijkstra or Floyd) if set
        if(path != null){
            for (int i = 0; i < path.size()-1; i++) {
                City city1 = path.get(i);
                City city2 = path.get(i+1);
                int city1x = map(city1.getLongitude(), minx, maxx, 10, width);
                int city1y = map(city1.getLatitude(), miny, maxy, 10, height);
                int city2x = map(city2.getLongitude(), minx, maxx, 10, width);
                int city2y = map(city2.getLatitude(), miny, maxy, 10, height);
                int mid = getHeight()/2;
                city1y = mid-(city1y-mid);
                city2y = mid-(city2y-mid);

                g.setColor(Color.RED);
                g.drawLine(city1x, city1y, city2x, city2y);
            }
        }

        polygon.reset();
    }

    /**
     * map a val from in_min-in_max to out_min-out_max
     * @param val value to be mapped
     * @param in_min min value from source
     * @param in_max max value from source
     * @param out_min min value from destination
     * @param out_max max value from destination
     * @return mapped value
     */
    private int map(int val, int in_min, int in_max, int out_min, int out_max)
    {
        return (val - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    //--------------------Setter-------------------------------
    public void setPath(ArrayList<City> path) {
        this.path = path;
    }
}
