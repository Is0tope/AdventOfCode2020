package Challenge14;

import Challenge13.BussesAndTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge14 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge14/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge14/input.txt"));
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
        HashMap<Long,Long> memory = new HashMap<>();
        BitMask mask = null;
        Pattern maskpat = Pattern.compile("mask = ([X01]+)");
        Pattern mempat = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
        for(String l : list){
            Matcher m = maskpat.matcher(l);
            if(m.find()){
                mask = new BitMask(m.group(1).toCharArray());
                continue;
            }
            m = mempat.matcher(l);
            if(m.find()){
                long address = Long.parseLong(m.group(1));
                long num = Long.parseLong(m.group(2));
                memory.put(address,mask.apply(num));
            }
        }
        long total = 0;
        for(long v : memory.values()){
            total += v;
        }
        return total;
    }

    public static long partB(ArrayList<String> list) {
        HashMap<Long,Long> memory = new HashMap<>();
        BitMask mask = null;
        Pattern maskpat = Pattern.compile("mask = ([X01]+)");
        Pattern mempat = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
        for(String l : list){
            Matcher m = maskpat.matcher(l);
            if(m.find()){
                mask = new BitMask(m.group(1).toCharArray());
                continue;
            }
            m = mempat.matcher(l);
            if(m.find()){
                long address = Long.parseLong(m.group(1));
                long num = Long.parseLong(m.group(2));
                ArrayList<Long> addresses = mask.applyMultiple(address);
                for(Long a : addresses){
                    memory.put(a,num);
                }
            }
        }
        long total = 0;
        for(long v : memory.values()){
            total += v;
        }
        return total;
    }
}
