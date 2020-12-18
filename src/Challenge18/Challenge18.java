package Challenge18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Challenge18 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge18/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge18/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<String> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while(reader.hasNextLine()){
                String l = reader.nextLine();
                list.add(l);
            }

            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(ArrayList<String> list) {
        long total = 0;
        for(String l : list){
            Expression ex = new Expression(l);
            long res = ex.evaluate("A");
            System.out.println(l + " = " + res);
            total += res;
        }
        return total;
    }

    public static long partB(ArrayList<String> list) {
        long total = 0;
        for(String l : list){
            Expression ex = new Expression(l);
            long res = ex.evaluate("B");
            System.out.println(l + " = " + res);
            total += res;
        }
        return total;
    }
}
