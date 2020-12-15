package Challenge15;

import Challenge14.BitMask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge15 {

    public static void main(String[] args) {
        Long resA = partAB(process("src/Challenge15/input.txt"),2020);
        System.out.println("Part A: " + resA.toString());
        Long resB = partAB(process("src/Challenge15/input.txt"),30000000);
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<Long> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<Long> list = new ArrayList<>();
            String[] strs = reader.nextLine().split(",");
            for(String s : strs) {
                list.add(Long.parseLong(s));
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void putInHashMapList(HashMap<Long,ArrayList<Long>> map, long num, long index){
        ArrayList<Long> l = null;
        if(!map.containsKey(num)){
            l = new ArrayList<Long>();
        }else{
            l = map.get(num);
        }
        l.add(index);
        map.put(num,l);
    }

    public static long partAB(ArrayList<Long> list, long target) {
        HashMap<Long,ArrayList<Long>> map = new HashMap<>();
        long cnt = 1;
        long lastspoken = 0;
        while(cnt <= list.size()){
            long spoken = list.get((int)cnt-1);
            putInHashMapList(map,spoken,cnt);
            cnt++;
            lastspoken = spoken;
        }
        while(cnt <= target){
            long spoken = 0;
            if(map.containsKey(lastspoken)){
                ArrayList<Long> l = map.get(lastspoken);
                if(l.size() < 2){
                    spoken = 0;
                }else{
                    spoken = l.get(l.size()-1) - l.get(l.size()-2);
                }
            }else{
                spoken = 0;
            }
            putInHashMapList(map,spoken,cnt);
            lastspoken = spoken;
            cnt++;
        }

        return lastspoken;
    }

}
