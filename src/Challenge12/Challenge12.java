package Challenge12;

import Challenge11.SeatingPlan;
import Util.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Challenge12 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge12/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge12/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<String> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<String> inst = new ArrayList<>();
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                inst.add(l);
            }
            return inst;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(ArrayList<String> instructions) {
        Point start = new Point(0,0);
        Ship ship = new Ship(start,90);
        for(String s : instructions){
            char command = s.toCharArray()[0];
            int number = Integer.parseInt(s.substring(1));
            switch(command){
                case 'N':
                    ship.move(0,number);
                    break;
                case 'E':
                    ship.move(90,number);
                    break;
                case 'S':
                    ship.move(180,number);
                    break;
                case 'W':
                    ship.move(270,number);
                    break;
                case 'F':
                    ship.moveForward(number);
                    break;
                case 'R':
                    ship.rotate(number);
                    break;
                case 'L':
                    ship.rotate(-number);
                    break;
            }
        }
        return ship.manhattanDistance(start);
    }

    public static long partB(ArrayList<String> instructions) {
        Point start = new Point(0,0);
        WaypointShip ship = new WaypointShip(start,new Point(10,1));
        for(String s : instructions){
            char command = s.toCharArray()[0];
            int number = Integer.parseInt(s.substring(1));
            switch(command){
                case 'N':
                    ship.moveWaypoint(0,number);
                    break;
                case 'E':
                    ship.moveWaypoint(90,number);
                    break;
                case 'S':
                    ship.moveWaypoint(180,number);
                    break;
                case 'W':
                    ship.moveWaypoint(270,number);
                    break;
                case 'F':
                    ship.moveForward(number);
                    break;
                case 'R':
                    ship.rotateWaypoint(number);
                    break;
                case 'L':
                    ship.rotateWaypoint(-number);
                    break;
            }
        }
        return ship.manhattanDistance(start);
    }
}
