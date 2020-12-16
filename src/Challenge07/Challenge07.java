package Challenge07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge07 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge07/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge07/input.txt"));
        System.out.println("Part B: " + resB.toString());
    }

    public static Bags process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            Bags bags = new Bags();
            final Pattern pat = Pattern.compile("(\\d+) (\\w+ \\w+)");
            while (reader.hasNextLine()) {
                String l = reader.nextLine();
                String[] parts1 = l.split(" bags contain ");
                String container = parts1[0];
                String parts2 = parts1[1];
                bags.addBag(container);
                if(!parts2.equals("no other bags.")){
                    for(String b: parts2.replace(".","").split((", "))){
                        Matcher m = pat.matcher(b);
                        m.find(); // Need below to throw if processing failed
                        bags.addRelationship(container,m.group(2),Long.parseLong(m.group(1)));
                    }
                }
            }
            return bags;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long countContainedBags(Bags bags, String bag){
        HashSet<String> contains = bags.contains(bag);
        long ret = 1;
        for(String b : contains){
            long mult = bags.getContainedBagCount(bag,b);
            ret += mult*countContainedBags(bags,b);
        }
        return ret;
    }

    public static long partA(Bags bags) {
        long cnt = 0;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> validBags = new HashSet<>();

        queue.add("shiny gold");
        while(queue.size() > 0){
            String bag = queue.remove();
            HashSet<String> containedBy = bags.containedBy(bag);
            validBags.addAll(containedBy);
            queue.addAll(containedBy);
        }
        return validBags.size();
    }

    public static long partB(Bags bags) {
        // Subtract one as not counting the shiny gold bag itself
        return countContainedBags(bags,"shiny gold") - 1;
    }
}
