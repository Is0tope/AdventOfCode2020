package Challenge23;

import Challenge22.RecursiveGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge23 {

    public static void main(String[] args) {
        String resA = partA("418976235");
        System.out.println("Part A: " + resA.toString());
        Long resB = partB("418976235");
        System.out.println("Part B: " + resB.toString());
    }

    public static String partA(String str) {
        CircularList circle = new CircularList();
        long minCup = Long.MAX_VALUE;
        long maxCup = Long.MIN_VALUE;
        for(char c : str.toCharArray()){
            long val = Long.parseLong(Character.toString(c));
            if(val > maxCup){
                maxCup = val;
            }
            if(val < minCup){
                minCup = val;
            }
            circle.addCup(new Cup(null,val));
        }
        long firstCup = Long.parseLong(Character.toString(str.charAt(0)));
        circle.setCurrent(firstCup);

        for(int i = 0;i < 100;i++){
            Cup beforeInsert = circle.getCurrent();
            ArrayList<Cup> cups = new ArrayList<>();
            cups.add(circle.popNext());
            cups.add(circle.popNext());
            cups.add(circle.popNext());
            long target = circle.getCurrent().getValue() - 1;
            while(!circle.contains(target)){
                target -= 1;
                if(target<minCup){
                    target=maxCup;
                }
            }
            circle.setCurrent(target);
            for(Cup c : cups){
                circle.addCup(c);
            }
            circle.setCurrent(beforeInsert.getNext().getValue());
        }
        // Print it out
        circle.setCurrent(1);
        Cup c = circle.getCurrent().getNext();
        String ret = "";
        while(c.getValue() != 1){
            ret += c.getValue();
            c = c.getNext();
        };
        return ret;
    }

    public static long partB(String str) {
        CircularList circle = new CircularList();
        long minCup = Long.MAX_VALUE;
        long maxCup = Long.MIN_VALUE;
        for(char c : str.toCharArray()){
            long val = Long.parseLong(Character.toString(c));
            if(val > maxCup){
                maxCup = val;
            }
            if(val < minCup){
                minCup = val;
            }
            circle.addCup(new Cup(null,val));
        }
        long val = maxCup+1;
        while(val <= 1_000_000){
            circle.addCup(new Cup(null,val));
            maxCup = val;
            val++;
        }
        long firstCup = Long.parseLong(Character.toString(str.charAt(0)));
        circle.setCurrent(firstCup);
        for(long i = 0;i < 10_000_000;i++){
            Cup beforeInsert = circle.getCurrent();
            ArrayList<Cup> cups = new ArrayList<>();
            cups.add(circle.popNext());
            cups.add(circle.popNext());
            cups.add(circle.popNext());
            long target = circle.getCurrent().getValue() - 1;
            while(!circle.contains(target)){
                target -= 1;
                if(target<minCup){
                    target=maxCup;
                }
            }
            circle.setCurrent(target);
            for(Cup c : cups){
                circle.addCup(c);
            }
            circle.setCurrent(beforeInsert.getNext().getValue());
        }
        circle.setCurrent(1L);
        Cup c = circle.getCurrent().getNext();
        long total = c.getValue();
        c = c.getNext();
        total *= c.getValue();
        return total;
    }
}
