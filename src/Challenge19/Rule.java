package Challenge19;

import java.util.ArrayList;

public class Rule {
    private String character;
    private ArrayList<ArrayList<Long>> rules;
    private boolean isChar;

    public Rule(String character) {
        this.character = character;
        this.isChar = true;
    }

    public Rule(ArrayList<ArrayList<Long>> rules) {
        this.rules = rules;
        this.isChar = false;
    }

    public String getCharacter() {
        return character;
    }

    public ArrayList<ArrayList<Long>> getRules() {
        return rules;
    }

    public boolean isChar() {
        return isChar;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "character='" + character + '\'' +
                ", rules=" + rules +
                ", isChar=" + isChar +
                '}';
    }
}
