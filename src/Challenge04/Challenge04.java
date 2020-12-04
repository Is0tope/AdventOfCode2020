package Challenge04;

import Challenge03.Slope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge04 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge04/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge04/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static ArrayList<HashMap<String,String>> process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            ArrayList<HashMap<String,String>> list = new ArrayList<>();
            while (reader.hasNextLine()) {
                HashMap<String,String> map = new HashMap<>();
                while (reader.hasNextLine()) {
                    String l = reader.nextLine();
                    if(l.isEmpty()){
                        break;
                    }
                    String[] tokens = l.split(" ");
                    for (String t : tokens) {
                       String[] kv = t.split(":");
                       map.put(kv[0],kv[1]);
                    }
                }
                list.add(map);
            }
            return list;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private static boolean validateHeight(String str){
        final Pattern pat = Pattern.compile("(\\d+)(cm|in)");
        Matcher m = pat.matcher(str);
        if(m.find()){
            int h = Integer.parseInt(m.group(1));
            String unit = m.group(2);
            if(unit.equals("cm")){
                return (h >= 150 && h<= 193);
            }else{
                return (h >= 59 && h<= 76);
            }

        }
        return false;
    }

    private static boolean validateHcl(String str){
        final Pattern pat = Pattern.compile("#[0-9a-f]{6}");
        Matcher m = pat.matcher(str);
        return m.matches();
    }

    private static boolean validatePid(String str){
        final Pattern pat = Pattern.compile("[0-9]{9}");
        Matcher m = pat.matcher(str);
        return m.matches();
    }

    public static boolean isPassportComplete(HashMap<String,String> passport){
        String[] mandatoryFields = new String[]{"byr","iyr","eyr","hgt","hcl","ecl","pid"};

        for (String mf : mandatoryFields) {
            if(!passport.containsKey(mf)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPassportValid(HashMap<String,String> passport) {
        if(!isPassportComplete(passport)){
            return false;
        }
        int byr = Integer.parseInt(passport.get("byr"));
        if(byr < 1920 || byr > 2002){
            return false;
        }
        int iyr = Integer.parseInt(passport.get("iyr"));
        if(iyr < 2010 || iyr > 2020){
            return false;
        }
        int eyr = Integer.parseInt(passport.get("eyr"));
        if(eyr < 2020 || eyr > 2030){
            return false;
        }
        if(!validateHeight(passport.get("hgt"))){
            return false;
        }
        if(!validateHcl(passport.get("hcl"))){
            return false;
        }
        List<String> validEyes = Arrays.asList(new String[]{"amb","blu","brn","gry","grn","hzl","oth"});
        if(!validEyes.contains(passport.get("ecl"))){
            return false;
        }
        if(!validatePid(passport.get("pid"))){
            return false;
        }
        return true;
    }

    public static long partA(ArrayList<HashMap<String,String>> list) {
        int valid = 0;
        for (HashMap<String,String> p : list) {
            if(isPassportComplete(p)){
                valid++;
            }
        }
        return valid;
    }

    public static long partB(ArrayList<HashMap<String,String>> list) {
        int valid = 0;
        for (HashMap<String,String> p : list) {
            if(isPassportValid(p)){
                valid++;
            }
        }
        return valid;
    }
}
