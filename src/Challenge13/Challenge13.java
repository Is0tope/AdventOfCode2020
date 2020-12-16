package Challenge13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Challenge13 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge13/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge13/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static BussesAndTime process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            HashSet<Long> busses = new HashSet<>();
            HashMap<Long,Long> offsets = new HashMap<>();
            long earliest = Long.parseLong(reader.nextLine());
            long offset = 0;
            for(String b : reader.nextLine().split(",")){
                if(!b.equals("x")){
                    busses.add(Long.parseLong(b));
                    offsets.put(Long.parseLong(b),offset);
                }
                offset++;

            }
            return new BussesAndTime(earliest,busses,offsets);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(BussesAndTime bt) {
        long minwait = Long.MAX_VALUE;
        long minbus = 0;
        for(long b : bt.getBusses()){
            long mod = bt.getEarliest() % b;
            long wait = mod == 0 ? 0 : b - mod;
            if(wait < minwait){
                minwait = wait;
                minbus = b;
            }
        }
        return minwait * minbus;
    }

    public static long modularMultiplicativeInverse(long num,long mod){
        long inv = 1;
        while((num*inv) % mod != 1){
            inv++;
        }
        return inv;
    }

    public static long partB(BussesAndTime bt) {
        HashMap<Long,Long> bs = bt.getBusOffset();
        long product = 1;
        for(long bus : bs.keySet()){
            product *= bus;
        }
        long result = 0;
        for(long bus : bs.keySet()){
            long offset = bs.get(bus) == 0 ? 0 : bus - bs.get(bus);
            long partial = product / bus;
            result += offset * partial * modularMultiplicativeInverse(partial,bus);
        }
        return result % product;
    }
}
