package Util;

import java.util.ArrayList;
import java.util.Objects;

public class Point4 {
    private long x;
    private long y;
    private long z;
    private long w;

    public Point4(long x, long y, long z, long w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public long getW() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point4 point4 = (Point4) o;
        return x == point4.x &&
                y == point4.y &&
                z == point4.z &&
                w == point4.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "Point4{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    public ArrayList<Point4> getNeighbours(){
        ArrayList<Point4> neighbours = new ArrayList<>();
        for(long i = -1;i <= 1;i++){
            for(long j = -1;j <= 1;j++) {
                for (long k = -1; k <= 1; k++) {
                    for (long v = -1; v <= 1; v++) {
                        Point4 p = new Point4(x + i, y + j, z + k, w + v);
                        if (p.equals(this)) {
                            continue;
                        }
                        neighbours.add(p);
                    }
                }
            }
        }
        return neighbours;
    }
}
