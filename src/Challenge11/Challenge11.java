package Challenge11;

import Util.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge11 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge11/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge11/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static SeatingPlan process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            SeatingPlan plan = new SeatingPlan();
            int y = 0;
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                int x = 0;
                for(char c : l.toCharArray()){
                    if(c=='L'){
                        plan.addSeat(x,y);
                    }
                    x++;
                }
                y++;
            }
            return plan;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(SeatingPlan plan) {
        HashMap<Point,Boolean> prev;
        do {
            //plan.drawPlan(10,10);
            //System.out.println("");
            prev = new HashMap<Point, Boolean>(plan.getSeats());
            plan.tick("A");

        } while(!plan.compareState(prev));
        return plan.getOccupiedCount();
    }

    public static long partB(SeatingPlan plan) {
        HashMap<Point,Boolean> prev;
        do {
            //plan.drawPlan(10,10);
            //System.out.println("");
            prev = new HashMap<Point, Boolean>(plan.getSeats());
            plan.tick("B");

        } while(!plan.compareState(prev));
        return plan.getOccupiedCount();
    }
}
