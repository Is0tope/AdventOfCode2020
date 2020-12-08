package Interpreter;

import Interpreter.commands.Acc;
import Interpreter.commands.ICommand;
import Interpreter.commands.Jmp;
import Interpreter.commands.Nop;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    private ArrayList<ICommand> instructions;
    private State state;

    public Interpreter(ArrayList<String> instructions) {
        this.instructions = compile(instructions);
        this.state = new State();
    }

    public ArrayList<ICommand> compile(ArrayList<String> code){
        ArrayList<ICommand> list = new ArrayList<>();
        final Pattern pat = Pattern.compile("(\\w+) ([\\+-]\\d+)");
        for(String line : code){
            Matcher m = pat.matcher(line);
            m.find(); // Break out if it doesn't match

            String inst = m.group(1);
            Integer num = Integer.parseInt(m.group(2));
            ICommand command = null;

            switch(inst){
                case "nop":
                    command = new Nop();
                    break;

                case "acc":
                    command = new Acc(num);
                    break;

                case "jmp":
                    command = new Jmp(num);
                    break;
            }
            list.add(command);
        }
        return list;
    }

    public void tick(){
        if(isTerminated()){
            return;
        }
        ICommand cmd = instructions.get(state.getPointer());
        cmd.execute(this.state);
    }

    public boolean isTerminated(){
        return state.getPointer() >= instructions.size();
    }

    public int getPointer() {
        return state.getPointer();
    }

    public long getAcc() {
        return state.getAcc();
    }

}
