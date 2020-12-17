package Util;

import java.util.ArrayList;
import java.util.Objects;

public class Point3 {
    private long x;
    private long y;
    private long z;

    public Point3(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3 point3 = (Point3) o;
        return x == point3.x &&
                y == point3.y &&
                z == point3.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public ArrayList<Point3> getNeighbours(){
        ArrayList<Point3> neighbours = new ArrayList<>();
        for(long i = -1;i <= 1;i++){
            for(long j = -1;j <= 1;j++) {
                for (long k = -1; k <= 1; k++) {
                    Point3 p = new Point3(x + i, y + j, z + k);
                    if (p.equals(this)) {
                        continue;
                    }
                    neighbours.add(p);
                }
            }
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return "Point3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
