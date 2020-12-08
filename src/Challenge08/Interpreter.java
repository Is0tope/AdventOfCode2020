package Challenge08;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    private ArrayList<String> instructions;
    private int pointer;

    // Variables
    private long acc;

    public boolean tick(){
        if(pointer >= instructions.size()){
            return false;
        }
        String line = instructions.get(pointer);
        final Pattern pat = Pattern.compile("(\\w+) ([\\+-])(\\d+)");
        Matcher m = pat.matcher(line);
        m.find(); // Break out if it doesn't match

        String inst = m.group(1);
        String dir = m.group(2);
        Integer num = Integer.parseInt(m.group(3));
        num *= dir.equals("-") ? -1 : 1;

        int jump = 1;
        switch(inst){
            case "nop":
                break;

            case "acc":
                acc += num;
                break;

            case "jmp":
                jump = num;
                break;
        }
        pointer += jump;
        return true;
    }

    public Interpreter(ArrayList<String> instructions) {
        this.instructions = instructions;
        this.acc = 0L;
        this.pointer = 0;
    }

    public int getPointer() {
        return pointer;
    }

    public long getAcc() {
        return acc;
    }


}
