package Challenge25;

import Util.PointHex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Challenge25 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge25/input.txt"));
        System.out.println("Part A: " + resA.toString());
    }

    public static ArrayList<Long> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<Long> list = new ArrayList<>();
            while(reader.hasNextLine()){
                list.add(Long.parseLong(reader.nextLine()));
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long getLoopSizeFromPublicKey(long subjectNumber,long publicKey){
        long value = 1;
        long loopSize = 0;
        do{
            loopSize++;
            value *= subjectNumber;
            value = value % 20201227L;
        }while(value != publicKey);
        return loopSize;
    }

    public static long generateEncryptionKey(long subjectNumber,long loopSize){
        long value = 1;
        for(long i = 0;i < loopSize;i++){
            value *= subjectNumber;
            value = value % 20201227L;
        }
        return value;
    }

    public static long partA(ArrayList<Long> list) {
        long cardPk = list.get(0);
        long doorPk = list.get(1);
        long cardLoop = getLoopSizeFromPublicKey(7,cardPk);
        return generateEncryptionKey(doorPk,cardLoop);
    }
}
