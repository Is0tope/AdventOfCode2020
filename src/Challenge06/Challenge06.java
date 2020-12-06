package Challenge06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge06 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge06/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge06/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<ArrayList<String>> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (reader.hasNextLine()) {
                ArrayList<String> vec = new ArrayList<>();
                while (reader.hasNextLine()) {
                    String l = reader.nextLine();
                    if(l.isEmpty()){
                        break;
                    }
                    vec.add(l);
                }
                list.add(vec);

            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long getAnswerCount(List<String> list){
        HashSet<Character> set = new HashSet<>();
        for(String v : list){
            for(Character c : v.toCharArray()){
                set.add(c);
            }
        }
        return (long)set.size();
    }

    public static long getCommonAnswerCount(List<String> list){
        HashSet<Character> prev = new HashSet<>();
        if(list.size() == 0){
            return 0;
        }
        // First set
        // Must be nicer way to add all the characters?
        for(Character c : list.get(0).toCharArray()){
            prev.add(c);
        }
        // Subsequent sets
        for(String v : list.subList(1,list.size())){
            HashSet<Character> next = new HashSet<>();
            for(Character c : v.toCharArray()){
                next.add(c);
            }
            prev.retainAll(next);
        }
        return (long)prev.size();
    }

    public static long partA(ArrayList<ArrayList<String>> list) {
        long cnt = 0;
        for(ArrayList<String> arr : list){
            cnt += getAnswerCount(arr);
        }
        return cnt;
    }

    public static long partB(ArrayList<ArrayList<String>> list) {
        long cnt = 0;
        for(ArrayList<String> arr : list){
            cnt += getCommonAnswerCount(arr);
        }
        return cnt;
    }
}
