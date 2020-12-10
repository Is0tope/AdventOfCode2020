package Challenge09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge09 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge09/input.txt"),25);
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge09/input.txt"),104054607);
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

    public static long partA(ArrayList<Long> list, int preamble) {
        for(int i = preamble; i < list.size();i++){
            if(!isSumOfTwo(list.subList(i-preamble,i),list.get(i))){
                return list.get(i);
            }
        }
        return -1;
    }

    public static long partB(ArrayList<Long> list, long target) {
        for(int i = 0; i<list.size();i++){
            long total = 0;
            for(int j = i;j<list.size();j++){
                total+=list.get(j);
                if(total == target && j-i>=1){
                    List<Long> sl = list.subList(i,j+1);
                    long max = Collections.max(sl);
                    long min = Collections.min(sl);
                    return  min + max;
                }
            }
        }
        return -1;
    }
}
