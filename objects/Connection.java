package objects;

/**
 * Created by matoran on 4/3/17.
 */
public class Connection {
    private int city1, city2;
    private int distance;

    public Connection(int city1, int city2, int distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    public int getCity1() {
        return city1;
    }

    public int getCity2() {
        return city2;
    }

    public int getDistance() {
        return distance;
    }

    public void updateCity1() {
        city1--;
    }

    public void updateCity2() {
        city2--;
    }
}
