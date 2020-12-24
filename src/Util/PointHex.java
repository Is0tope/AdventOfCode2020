package Util;

import java.util.ArrayList;
import java.util.Objects;

public class PointHex {
    private long x;
    private long y;
    private long z;

    public PointHex(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PointHex move(String dir){
        long x = this.x;
        long y = this.y;
        long z = this.z;
        switch(dir){
            case "e":
                x++;
                y--;
                break;
            case "w":
                x--;
                y++;
                break;
            case "se":
                z++;
                y--;
                break;
            case "sw":
                z++;
                x--;
                break;
            case "ne":
                x++;
                z--;
                break;
            case "nw":
                y++;
                z--;
                break;
        }
        return new PointHex(x,y,z);
    }

    public ArrayList<PointHex> getNeighbours(){
        String[] dirs = {"e","w","ne","nw","se","sw"};
        ArrayList<PointHex> list = new ArrayList<>();
        for(String d : dirs){
            list.add(move(d));
        }
        return list;
    }

    @Override
    public String toString() {
        return "PointHex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointHex pointHex = (PointHex) o;
        return x == pointHex.x && y == pointHex.y && z == pointHex.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
