package Challenge12;

import Util.Point;

public class Ship {
    private Point position;
    private int bearing;

    public Ship(Point position, int bearing) {
        this.position = position;
        this.bearing = bearing;
    }

    public void rotate(int angle){
        bearing = (360 + bearing + angle) % 360;
    }

    public void move(int angle, int distance){
        switch(angle){
            case 0:
                position = new Point(position.getX(), position.getY()+distance);
                break;
            case 90:
                position = new Point(position.getX()+distance, position.getY());
                break;
            case 180:
                position = new Point(position.getX(), position.getY()-distance);
                break;
            case 270:
                position = new Point(position.getX()-distance, position.getY());
                break;
        }
    }

    public void moveForward(int distance){
        move(bearing,distance);
    }

    public long manhattanDistance(Point p){
        return Math.abs(p.getX()-position.getX()) + Math.abs(p.getY()-position.getY());
    }

    public Point getPosition() {
        return position;
    }

    public int getBearing() {
        return bearing;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "position=" + position +
                ", bearing=" + bearing +
                '}';
    }
}
