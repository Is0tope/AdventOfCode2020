package Challenge17;

import Challenge16.TicketBundle;
import Challenge16.TicketRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge17 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge17/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process2("src/Challenge17/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static CubeReactor process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            CubeReactor reactor = new CubeReactor();
            long y = 0;
            while(reader.hasNextLine()){
                String l = reader.nextLine();
                for(int x = 0;x<l.length();x++){
                    if(l.charAt(x) == '#'){
                        reactor.addCube(x,y,0,true);
                    }
                }
                y++;
            }

            return reactor;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static HyperCubeReactor process2(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            HyperCubeReactor reactor = new HyperCubeReactor();
            long y = 0;
            while(reader.hasNextLine()){
                String l = reader.nextLine();
                for(int x = 0;x<l.length();x++){
                    if(l.charAt(x) == '#'){
                        reactor.addCube(x,y,0,0,true);
                    }
                }
                y++;
            }

            return reactor;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    public static long partA(CubeReactor reactor) {
        for(long i = 0;i< 6;i++){
            reactor.tick();
        }
        return reactor.activeCount();
    }

    public static long partB(HyperCubeReactor reactor) {
        for(long i = 0;i< 6;i++){
            reactor.tick();
        }
        return reactor.activeCount();
    }
}
