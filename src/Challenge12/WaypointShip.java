package Challenge12;

import Util.Point;

public class WaypointShip {
    private Point position;
    private Point waypoint;

    public WaypointShip(Point position, Point waypoint) {
        this.position = position;
        this.waypoint = waypoint;
    }

    public void rotateWaypoint(int angle){
        waypoint = Point.rotate(waypoint,angle);
    }

    public void moveWaypoint(int angle, int distance){
        switch(angle){
            case 0:
                waypoint = new Point(waypoint.getX(), waypoint.getY()+distance);
                break;
            case 90:
                waypoint = new Point(waypoint.getX()+distance, waypoint.getY());
                break;
            case 180:
                waypoint = new Point(waypoint.getX(), waypoint.getY()-distance);
                break;
            case 270:
                waypoint = new Point(waypoint.getX()-distance, waypoint.getY());
                break;
        }
    }

    public void moveForward(int distance){
        position = Point.add(position,Point.multiply(waypoint,distance));
    }

    public long manhattanDistance(Point p){
        return Math.abs(p.getX()-position.getX()) + Math.abs(p.getY()-position.getY());
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "WaypointShip{" +
                "position=" + position +
                ", waypoint=" + waypoint +
                '}';
    }
}
