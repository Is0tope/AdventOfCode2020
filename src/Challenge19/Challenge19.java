package Challenge19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge19 {

    private static RuleSet rules = new RuleSet();

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge19/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge19/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<String> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String l = reader.nextLine();
                if(l.isEmpty()){
                    break;
                }
                String[] tmp = l.split(":");
                long num = Long.parseLong(tmp[0]);
                Pattern pat = Pattern.compile("\"(\\w)\"");
                Matcher m = pat.matcher(tmp[1]);
                Rule rule = null;
                if(m.find()){
                    rule = new Rule(m.group(1));
                }else{
                    ArrayList<ArrayList<Long>> lst = new ArrayList<>();
                    String[] groups = tmp[1].split("\\|");
                    for(String g : groups){
                        ArrayList<Long> sublst = new ArrayList<>();
                        for(String n : g.trim().split(" ")){
                            sublst.add(Long.parseLong(n));
                        }
                        lst.add(sublst);
                    }
                    rule = new Rule(lst);
                }
                rules.addRule(num,rule);
            }
            ArrayList<String> list = new ArrayList<>();
            while(reader.hasNextLine()) {
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

    public static ArrayList<String> crossArrays(ArrayList<String> a1, ArrayList<String> a2){
        ArrayList<String> ret = new ArrayList<>();
        for(String s1 : a1){
            for(String s2 : a2){
                ret.add(s1+s2);
            }
        }
        return ret;
    }

    public static ArrayList<String> textForRule(ArrayList<Long> targets){
        ArrayList<String> ret = new ArrayList<>();
        for(int i = 0;i < targets.size();i++){
            long t = targets.get(i);
            Rule rule = rules.getRule(t);
            ArrayList<String> lst = new ArrayList<>();
            if(rule.isChar()){
                lst.add(rule.getCharacter());
            }else{
                ArrayList<ArrayList<Long>> sr = rule.getRules();
                for(ArrayList<Long> rl : sr){
                    ArrayList<Long> tmp = new ArrayList<>(rl);
                    lst.addAll(textForRule(rl));
                }
            }
            if(i == 0){
                ret = lst;
            }else{
                ret = crossArrays(ret,lst);
            }
        }
        return ret;
    }

    public static ArrayList<ArrayList<Long>> crossLists(ArrayList<ArrayList<Long>> l1, ArrayList<ArrayList<Long>> l2){
        ArrayList<ArrayList<Long>> ret = new ArrayList<>();
        for(ArrayList<Long> c1 : l1){
            for(ArrayList<Long> c2 : l2){
                ArrayList<Long> tmp = new ArrayList<>();
                tmp.addAll(c1);
                tmp.addAll(c2);
                ret.add(tmp);
            }
        }
        return ret;
    }

    public static long partA(ArrayList<String> list) {
        System.out.println(rules);
        HashSet<String> valid = new HashSet<>(textForRule(new ArrayList<Long>(Arrays.asList(0L))));
        long validCount = 0;
        for(String l : list){
            if(valid.contains(l)){
                validCount++;
            }
        }
        return validCount;
    }

    public static boolean validateLoop(String str){
        // No intersection between these apparently...
        HashSet<String> valid42 = new HashSet<>(textForRule(new ArrayList<Long>(Arrays.asList(42L))));
        HashSet<String> valid31 = new HashSet<>(textForRule(new ArrayList<Long>(Arrays.asList(31L))));
        // Validate the sets
        int len = valid42.iterator().next().length();
        for(String s : valid42){
            if(s.length() != len){
                System.out.println("ERROR: Different sized strings");
                System.exit(1);
            }
        }
        for(String s : valid31){
            if(s.length() != len){
                System.out.println("ERROR: Different sized strings");
                System.exit(1);
            }
        }
        if(str.length() % len != 0){
            return false;
        }
        ArrayList<String> tokens = new ArrayList<>();
        for(int i = 0;i < str.length();i+=len){
            tokens.add(str.substring(i,i+len));
        }
        HashMap<String,Long> map = new HashMap<>();
        for(String v : valid42){
            map.put(v,42L);
        }
        for(String v : valid31){
            map.put(v,31L);
        }
        long currentMode = 42;
        long num42 = 0;
        long num31 = 0;
        for(String t : tokens){
            long num = map.getOrDefault(t,0L);
            if(num == 0){
                return false;
            }
            if(currentMode == 42){
                if(num == 42){
                    num42++;
                }else{
                    currentMode = 31;
                    num31++;
                }
                continue;
            }
            if(currentMode == 31){
                if(num == 31){
                    num31++;
                }else{
                    return false;
                }
            }
        }
        if(num42 - num31 < 1 || num42 == 0 || num31 == 0){
            return false;
        }
        return true;
    }
    public static long partB(ArrayList<String> list) {
        long validCount = 0;
        for(String l : list){
            boolean res = validateLoop(l);
            if(res){
                validCount++;
            }
        }
        return validCount;
    }
}
