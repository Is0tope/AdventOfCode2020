package Challenge18;

import java.util.ArrayList;
import java.util.Stack;

public class Expression {
    private String expression;

    public Expression(String expression) {
        this.expression = expression.replace(" ","");
    }

    private long reduce(ArrayList<Operation> list){
        long total = 0;
        for(Operation o : list){
            total = o.eval(total);
        }
        return total;
    }

    private long reduce2(ArrayList<Operation> list){
        ArrayList<Operation> ret = new ArrayList<>();
        Operation lastOp = null;  // This works because in any list the first op must be a blank
        for(Operation o : list){
            if(o.getTyp() == '*' || o.getTyp() == ' '){
                ret.add(o);
                lastOp = o;
            }
            if(o.getTyp() == '+'){
                lastOp.setNum(lastOp.getNum() + o.getNum());
            }
        }
        return reduce(ret);
    }

    public long evaluate(String typ){
        Stack<ArrayList<Operation>> stack = new Stack<>();
        ArrayList<Operation> list = new ArrayList<>();
        char lastInstruction = ' ';
        for(char c : expression.toCharArray()){
            if(c == '('){
                list.add(new Operation(lastInstruction));
                stack.push(list);
                list = new ArrayList<Operation>();
                lastInstruction = ' ';
            }
            if(c == ')'){
                long listTotal = typ.equals("A") ? reduce(list) : reduce2(list);
                list = stack.pop();
                list.get(list.size()-1).setNum(listTotal);
            }
            if("+*".contains(Character.toString(c))){
                lastInstruction = c;
            }
            if("0123456789".contains(Character.toString(c))){
                list.add(new Operation(lastInstruction,Long.parseLong(Character.toString(c))));
            }
        }
        return typ.equals("A") ? reduce(list) : reduce2(list);
    }
}
