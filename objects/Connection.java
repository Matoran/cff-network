package objects;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date March and April 2017
 * @file Connection.java
 *
 * Connection between cities
 *
 */
public class Connection {
    //--------------------Attributes----------------------------
    private int city1, city2;
    private int distance;

    //--------------------Methods-------------------------------
    /**
     * Connected two cities
     * @param city1 name of city1
     * @param city2 name of city2
     * @param distance between city1 and city2
     */
    public Connection(int city1, int city2, int distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    //--------------------Getter-------------------------------
    public int getCity1() {
        return city1;
    }

    public int getCity2() {
        return city2;
    }

    public int getDistance() {
        return distance;
    }

    //Use for the deletion
    public void updateCity1() {
        city1--;
    }

    public void updateCity2() {
        city2--;
    }
}
