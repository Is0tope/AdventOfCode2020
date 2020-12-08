package Challenge08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Challenge08 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge08/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge08/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<String> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while (reader.hasNextLine()) {
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
        Interpreter interpreter = new Interpreter(list);
        HashSet<Integer> seen = new HashSet<>();

        while(!seen.contains(interpreter.getPointer())){
            seen.add(interpreter.getPointer());
            interpreter.tick();
        }
        return interpreter.getAcc();
    }

    public static long partB(ArrayList<String> list) {
        for(int i = 0;i<list.size();i++){
            ArrayList<String> modifiedInst = new ArrayList<>();
            for(int j = 0;j<list.size();j++){
                String inst = list.get(j);
                if(i==j){
                    String cmd = inst.substring(0,3);
                    if(cmd.equals("jmp")){
                        inst = inst.replace("jmp","nop");
                    }
                    if(cmd.equals("nop")){
                        inst = inst.replace("nop","jmp");
                    }
                }
                modifiedInst.add(inst);
            }

            Interpreter interpreter = new Interpreter(modifiedInst);
            HashSet<Integer> seen = new HashSet<>();

            while(!seen.contains(interpreter.getPointer())){
                seen.add(interpreter.getPointer());
                // If returns false, it terminated correctly
                if(!interpreter.tick()){
                    return interpreter.getAcc();
                }
            }
        }
        return -1;
    }
}
