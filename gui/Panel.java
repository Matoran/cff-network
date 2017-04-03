package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by matoran on 4/3/17.
 */
public class Panel extends JPanel{
    private ArrayList<Point> points = new ArrayList<>();
    private Polygon polygon = new Polygon();
    private int minx = Integer.MAX_VALUE, miny =  Integer.MAX_VALUE, maxx = -1, maxy = -1;

    public Panel() {

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point point : points) {
            int x = map(point.x, minx, maxx, 10, getWidth()-10);
            int y = map(point.y, miny, maxy, 10, getHeight()-10);
            int mid = getHeight()/2;
            y = mid-(y-mid);
            //point.setLocation(point.x, mid-(point.y-mid));
            //System.out.println("x" + map(point.x, minx, maxx, getWidth()));
           // System.out.println("y" + map(point.y, miny, maxy, getHeight()));
            polygon.addPoint(x, y);
        }
        g.drawPolygon(polygon);
        polygon.reset();
    }

    private int map(int val, int in_min, int in_max, int out_min, int out_max)
    {
        return (val - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

}
