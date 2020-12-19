package Challenge19;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RuleSet {
    private HashMap<Long,Rule> rules;
    private HashMap<ArrayList<Long>,ArrayList<Long>> inverse;
    private HashMap<String,ArrayList<Long>> letters;
    public RuleSet() {
        this.rules = new HashMap<>();
        this.inverse = new HashMap<>();
        this.letters = new HashMap<>();
    }

    public void addRule(long num,Rule rule){
        rules.put(num,rule);
        if(rule.isChar()){
            ArrayList<Long> tmp = letters.getOrDefault(rule.getCharacter(),new ArrayList<Long>());
            tmp.add(num);
            letters.put(rule.getCharacter(),tmp);
        }else{
            for(ArrayList<Long> rl : rule.getRules()){
                ArrayList<Long> tmp = inverse.getOrDefault(rl,new ArrayList<Long>());
                tmp.add(num);
                inverse.put(rl,tmp);
            }
        }
    }

    public Rule getRule(long num){
        return rules.get(num);
    }

    public HashMap<ArrayList<Long>, ArrayList<Long>> getInverse() {
        return inverse;
    }

    public HashMap<String, ArrayList<Long>> getLetters() {
        return letters;
    }

    @Override
    public String toString() {
        return "RuleSet{" +
                "rules=" + rules +
                ", inverse=" + inverse +
                ", letters=" + letters +
                '}';
    }
}
