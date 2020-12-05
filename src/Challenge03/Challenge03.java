package Challenge03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Challenge03 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge03/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge03/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static Slope process(String fname) {
        File file = new File(fname);
        HashSet<String> set = new HashSet<>();
        try {
            Scanner reader = new Scanner(file);
            int height = 0;
            int width = 0;
            Slope slope = new Slope();
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                width = l.length();
                char[] arr = l.toCharArray();
                for (int i = 0; i < width; i++) {
                    if(arr[i] == '#'){
                        slope.addTree(i,height);
                    }
                }
                height++;
            }
            slope.setHeight(height);
            slope.setWidth(width);
            return slope;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long calcPath(int xv,int yv,Slope slope){
        int xdist = 0;
        int ydist = 0;
        int trees = 0;

        while(ydist < slope.getHeight()){
            if(slope.isTree(xdist,ydist)){
                trees++;
            }
            xdist+=xv;
            ydist+=yv;
        }

        return trees;
    }

    public static long partA(Slope slope) {
        return calcPath(3,1,slope);
    }

    public static long partB(Slope slope) {
        ArrayList<Integer[]> arr = new ArrayList<>();
        arr.add(new Integer[]{1,1});
        arr.add(new Integer[]{3,1});
        arr.add(new Integer[]{5,1});
        arr.add(new Integer[]{7,1});
        arr.add(new Integer[]{1,2});

        long total = 1;
        for (Integer[] vector : arr){
            total *= calcPath(vector[0],vector[1],slope);
        }
        return total;
    }
}
