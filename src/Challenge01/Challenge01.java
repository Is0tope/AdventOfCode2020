package Challenge01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Challenge01 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge01/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge01/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<Long> process(String fname) {
        File file = new File(fname);
        ArrayList<Long> arr = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                arr.add(Long.parseLong(l));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return arr;
    }

    public static long partA(ArrayList<Long> nums) {
        HashSet<Long> set = new HashSet<>(nums);
        for(long n : nums) {
            long find = 2020 - n;
            if( n == find ){
                // Assume that all numbers in the list are unique
                continue;
            }
            if(set.contains(find)){
               return n * find;
            }
        }
        return 0;
    }

    public static long partB(ArrayList<Long> nums) {
        // This is awful
        for (int i = 0; i<nums.size()-2; i++){
            for (int j = i+1; j<nums.size()-1; j++){
                for (int k = j+1; k<nums.size(); k++){
                    long a = nums.get(i);
                    long b = nums.get(j);
                    long c = nums.get(k);
                    if(a+b+c == 2020){
                        return a*b*c;
                    }
                }
            }
        }
        return 0;
    }
}
