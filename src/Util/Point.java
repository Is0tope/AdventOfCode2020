package Util;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Point multiply(Point p,int scalar){
        return new Point(p.getX()*scalar,p.getY()*scalar);
    }

    public static Point add(Point p, Point p2){
        return new Point(p.getX()+p2.getX(),p.getY()+p2.getY());
    }

    public static Point rotate(Point p,int angle){
        // Use matrix rotation around y axis, integer math requires janky rounding. Could use angle -> trig mappings i guess.
        double c = Math.cos(Math.toRadians(angle));
        double s = Math.sin(Math.toRadians(angle));
        return new Point((int)Math.round(p.getX()*c + p.getY()*s),(int)Math.round(p.getY()*c - p.getX()*s));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
