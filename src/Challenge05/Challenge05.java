package Challenge05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge05 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge05/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge05/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<String> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while (reader.hasNextLine()) {
                while (reader.hasNextLine()) {
                    String l = reader.nextLine();
                    list.add(l);
                }
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long getSeatID(String ticket){
        String rowStr = ticket.substring(0,7);
        int row = Integer.parseInt(rowStr.replace("F","0").replace("B","1"),2);
        String seatStr = ticket.substring(7);
        int col = Integer.parseInt(seatStr.replace("L","0").replace("R","1"),2);
        return (row*8)+col;
    }

    public static long partA(ArrayList<String> list) {
        long maxID = 0;
        for(String t : list){
            long id = getSeatID(t);
            if(id > maxID){
                maxID = id;
            }
        }
        return maxID;
    }

    public static long partB(ArrayList<String> list) {
        HashSet<Long> set = new HashSet<>();
        for(String t : list){
            long id = getSeatID(t);
            set.add(id);
        }

        for(long i = 0;i<8*128;i++){
            if(!set.contains(i)){
                if(set.contains(i-1) && set.contains(i+1)){
                    System.out.println("Possible Match: ID=" + i + " ROW=" + Math.floorDiv(i,8) + " COL=" + i%8);
                    return i;
                }
            }
        }
        return -1;
    }
}
