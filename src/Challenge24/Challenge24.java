package Challenge24;

import Util.PointHex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Challenge24 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge24/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge24/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<String> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while(reader.hasNextLine()){
                list.add(reader.nextLine());
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(ArrayList<String> list) {
        // White = True, Black = False
        HashMap<PointHex,Boolean> map = new HashMap<>();
        for(String l : list){
            int start = 0;
            int end = 1;
            PointHex p = new PointHex(0,0,0);
            while(end <= l.length()){
                String ss = l.substring(start,end);
                if(ss.equals("n") || ss.equals("s")){
                    end++;
                    ss = l.substring(start,end);
                }
                p = p.move(ss);
                start=end;
                end++;
            }
            boolean current = map.getOrDefault(p,true);
            map.put(p,!current);
        }
        long total = 0;
        for(boolean v : map.values()){
            if(!v){
                total++;
            }
        }
        return total;
    }

    public static long partB(ArrayList<String> list) {
        // White = True, Black = False
        HashMap<PointHex,Boolean> map = new HashMap<>();
        for(String l : list){
            int start = 0;
            int end = 1;
            PointHex p = new PointHex(0,0,0);
            while(end <= l.length()){
                String ss = l.substring(start,end);
                if(ss.equals("n") || ss.equals("s")){
                    end++;
                    ss = l.substring(start,end);
                }
                p = p.move(ss);
                start=end;
                end++;
            }
            boolean current = map.getOrDefault(p,true);
            map.put(p,!current);
        }

        for(int i = 0;i < 100;i++){
            HashMap<PointHex,Boolean> newMap = new HashMap<>(map);
            HashSet<PointHex> toCheck = new HashSet<>();
            for(PointHex p : map.keySet()){
                toCheck.add(p);
                toCheck.addAll(p.getNeighbours());
            }
            for(PointHex p : toCheck){
                ArrayList<PointHex> neighbours = p.getNeighbours();
                int numBlack = 0;
                for(PointHex n : neighbours){
                    if(!map.getOrDefault(n,true)){
                        numBlack++;
                    }
                }
                boolean colour = map.getOrDefault(p,true);
                boolean newColour = colour;
                if(colour){
                    if(numBlack == 2){
                        newColour = false;
                    }
                }else{
                    if(numBlack == 0 || numBlack > 2){
                        newColour = true;
                    }
                }
                newMap.put(p,newColour);
            }
            map = newMap;
        }
        long total = 0;
        for(boolean v : map.values()){
            if(!v){
                total++;
            }
        }
       return total;
    }
}
