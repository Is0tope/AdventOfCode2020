package Challenge02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge02 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge02/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge02/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<Password> process(String fname) {
        File file = new File(fname);
        ArrayList<Password> arr = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            final Pattern pat = Pattern.compile("(\\d+)-(\\d+) (\\w+): (\\w+)");
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                Matcher m = pat.matcher(l);
                if(m.find()){
                    Password p = new Password(
                            Integer.parseInt(m.group(1)),
                            Integer.parseInt(m.group(2)),
                            m.group(3).charAt(0),
                            m.group(4));
                    arr.add(p);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return arr;
    }

    public static long partA(ArrayList<Password> pwds) {
        int valid = 0;
        for(Password p : pwds) {
            if(p.isValidA()){
                valid++;
            }
        }
        return valid;
    }

    public static long partB(ArrayList<Password> pwds) {
        int valid = 0;
        for(Password p : pwds) {
            if(p.isValidB()){
                valid++;
            }
        }
        return valid;
    }
}
