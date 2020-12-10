package Challenge10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge10 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge10/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge10/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<Long> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<Long> list = new ArrayList<>();
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                list.add(Long.parseLong(l));
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static boolean isSumOfTwo(List<Long> list, long num){
        HashSet<Long> set = new HashSet<>(list);
        for(Long i : list){
            long comp = num - i;
            if(set.contains(comp)){
                return true;
            }
        }
        return false;
    }

    public static long partA(ArrayList<Long> list) {
        Collections.sort(list);
        HashMap<Long,Integer> deltas = new HashMap();
        long current = 0;
        long target = Collections.max(list) + 3;
        for(Long a : list){
            long delta = a - current;
            deltas.put(delta,deltas.getOrDefault(delta,0)+1);
            current = a;
        }
        deltas.put(target-current,deltas.getOrDefault(target-current,0)+1);
        return (long)deltas.getOrDefault(1L,0) * (long)deltas.getOrDefault(3L,0);
    }

    public static long getCombinations(ArrayList<Long> list,int index){
        if(index==list.size()-1){
            return 1;
        }
        long current = list.get(index);
        ArrayList<Integer> options = new ArrayList<>();
        for(int i = index+1;i<Math.min(index+4,list.size());i++){
            if(list.get(i) - current <= 3){
                options.add(i);
            }
        }
        long combinations = 0;
        for(Integer o : options){
            combinations += getCombinations(list,o);
        }
        return combinations;
    }

    public static long partB(ArrayList<Long> list) {
        long target = Collections.max(list) + 3;
        list.add(0L);
        list.add(target);
        Collections.sort(list);
        // The only deltas between the numbers are 1 or 3. 3's can be skipped as they must remain in position.
        // Look for clusters of 1's. In the set there are a maximum of 5 consecutive digits, so figure out the
        // combinations for 1 to 5 and apply it when you find a group.
        HashMap<Integer,Long> costs = new HashMap<>();
        ArrayList<Long> tmp = new ArrayList<Long>();
        for(long i = 0;i < 5;i++){
            tmp.add(i);
            costs.put(tmp.size(),getCombinations(tmp,0));
        }
        long combinations = 1;
        int start = 0;
        long previous = 0;
        for(int i = 1;i < list.size();i++){
            long current = list.get(i);
            if(current-previous > 1){
                combinations *= costs.get(i-start);
                start = i;
            }
            previous = current;
        }
        return combinations;
    }
}
